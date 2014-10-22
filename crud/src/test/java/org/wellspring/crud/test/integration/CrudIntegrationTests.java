package org.wellspring.crud.test.integration;

import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.data.domain.Persistable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.wellspring.crud.config.AppConfiguration;
import org.wellspring.crud.controller.CrudController;
import org.wellspring.crud.controller.impl.rest.RestCrudControllerImpl;
import org.wellspring.crud.persistence.repository.CrudRepository;
import org.wellspring.crud.service.CrudService;
import org.wellspring.crud.util.CrudConstants;
import org.wellspring.crud.util.PackageConstants;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;

@ComponentScan("org.wellspring.crud")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfiguration.class })
@WebAppConfiguration
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class CrudIntegrationTests<S extends CrudService<R, T, ID>, R extends CrudRepository<T, ID>, T extends Persistable<ID>, ID extends Serializable>
		extends TestCase {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(CrudIntegrationTests.class);

	@Autowired
	private org.springframework.context.ApplicationContext applicationContext;

	ObjectMapper mapper = new ObjectMapper();

	private List<CrudController<S, R, T, ID>> crudControllers = new ArrayList<CrudController<S, R, T, ID>>();

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;

	T getRawObject(final MvcResult mvcResult, Class<T> clazz) throws Exception {
		return new ObjectMapper().readValue(mvcResult.getResponse()
				.getContentAsString(), clazz);
	}

	public static byte[] convertObjectToJsonBytes(Object object)
			throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		return mapper.writeValueAsBytes(object);
	}

	@Before
	public void setup() throws ClassNotFoundException {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

		ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(
				true);

		scanner.addIncludeFilter(new AnnotationTypeFilter(Controller.class));

		for (BeanDefinition bd : scanner
				.findCandidateComponents(PackageConstants.CONTROLLER_PACKAGE)) {
			Class<?> clazz = Class.forName(bd.getBeanClassName());

			Object object = applicationContext.getBean(clazz);
			if (object instanceof RestCrudControllerImpl) {
				LOGGER.info("adding for crud Testing " + bd.getBeanClassName()
						+ "...");
				@SuppressWarnings("unchecked")
				CrudController<S, R, T, ID> controller = (CrudController<S, R, T, ID>) applicationContext
						.getBean(clazz);

				crudControllers.add(controller);
			}
		}
	}

	@Test
	public void testControllersQuantity() {
		assertFalse("controller quantity zero", crudControllers.isEmpty());
	}

	@Test
	public void testSave() throws JsonProcessingException, Exception {
		MvcResult mvcResult;
		for (CrudController<S, R, T, ID> controller : crudControllers) {

			ParameterizedType genericSuperclass = (ParameterizedType) controller
					.getClass().getGenericSuperclass();
			Class<T> basicEntityClazz = (Class<T>) genericSuperclass
					.getActualTypeArguments()[2];

			RequestMapping requestMapping = controller.getClass()
					.getAnnotation(RequestMapping.class);

			assertNotNull("requestMapping expected", requestMapping);

			assertNotNull("requestMapping url expected", requestMapping.value());

			String basicMapping = requestMapping.value()[0];

			Persistable<ID> basicEntity = basicEntityClazz.newInstance();
			Persistable<ID> basicEntity2 = basicEntityClazz.newInstance();
			Persistable<ID> basicEntity3 = basicEntityClazz.newInstance();
			mvcResult = saveTest(basicEntity, basicMapping);
			// basicEntity = mapper.readValue(mvcResult.getResponse()
			// .getContentAsString(), basicEntityClazz);
			Object id = JsonPath.read(mvcResult.getResponse()
					.getContentAsString(), "$.id");
			Collection<Persistable<ID>> entities = new ArrayList<Persistable<ID>>();
			entities.add(basicEntity);
			entities.add(basicEntity2);
			entities.add(basicEntity3);
			/*
			 * mvcResult = mockMvc .perform( post(basicMapping +
			 * CrudConstants.OPERATION_SAVE_ENTITIES)
			 * .contentType(MediaType.APPLICATION_JSON) .content(
			 * mapper.writeValueAsString(entities.iterator())))
			 * .andExpect(status().isOk())
			 * 
			 * .andDo(print()).andReturn();
			 */
			countTest(basicMapping);
			saveTest(basicEntity2, basicMapping);
			saveTest(basicEntity, basicMapping);
			countTest(basicMapping);
			saveTest(basicEntity, basicMapping);
			findOneTest(id, basicMapping);
			existsTest(id, basicMapping);
			deleteByIdTest(id, basicMapping);
			countTest(basicMapping);
			deleteByEntityTest(basicEntity2, basicMapping);
			countTest(basicMapping);
			deleteAllTest(basicMapping);
		}
	}

	private MvcResult saveTest(Persistable<ID> basicEntity, String basicMapping)
			throws JsonProcessingException, Exception {
		return mockMvc
				.perform(
						post(basicMapping + CrudConstants.OPERATION_SAVE)
								.contentType(MediaType.APPLICATION_JSON)
								.content(mapper.writeValueAsString(basicEntity)))
				.andExpect(status().isOk())

				.andExpect((jsonPath("$.id", notNullValue()))).andDo(print())
				.andReturn();
	}

	private MvcResult countTest(String basicMapping)
			throws JsonProcessingException, Exception {
		return mockMvc
				.perform(
						post(basicMapping + CrudConstants.OPERATION_COUNT)
								.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())

				.andExpect((jsonPath("$", notNullValue()))).andDo(print())
				.andReturn();
	}

	private MvcResult findOneTest(Object id, String basicMapping)
			throws JsonProcessingException, Exception {
		return mockMvc
				.perform(
						get(
								basicMapping + CrudConstants.OPERATION_FIND_ONE
										+ "/" + id).contentType(
								MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())

				.andExpect((jsonPath("$.id", notNullValue())))

				.andExpect((jsonPath("$.id").value(id)))

				.andDo(print()).andReturn();
	}

	private MvcResult existsTest(Object id, String basicMapping)
			throws JsonProcessingException, Exception {
		return mockMvc
				.perform(
						get(
								basicMapping + CrudConstants.OPERATION_EXISTS
										+ "/" + id).contentType(
								MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())

				.andExpect((jsonPath("$", notNullValue())))

				.andDo(print()).andReturn();
	}

	public MvcResult deleteByIdTest(Object id, String basicMapping)
			throws JsonProcessingException, Exception {
		return mockMvc
				.perform(
						get(
								basicMapping
										+ CrudConstants.OPERATION_DELETE_BY_ID
										+ "/" + id).contentType(
								MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andDo(print()).andReturn();
	}

	public MvcResult deleteByEntityTest(Persistable<ID> basicEntity,
			String basicMapping) throws JsonProcessingException, Exception {
		return mockMvc
				.perform(
						post(
								basicMapping
										+ CrudConstants.OPERATION_DELETE_BY_ENTITY)
								.contentType(MediaType.APPLICATION_JSON)
								.content(mapper.writeValueAsString(basicEntity)))
				.andExpect(status().isOk()).andDo(print()).andReturn();
	}

	public MvcResult deleteAllTest(String basicMapping)
			throws JsonProcessingException, Exception {
		return mockMvc
				.perform(
						post(basicMapping + CrudConstants.OPERATION_DELETE_ALL)
								.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andDo(print()).andReturn();
	}
}
