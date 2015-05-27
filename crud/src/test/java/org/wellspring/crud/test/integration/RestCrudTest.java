package org.wellspring.crud.test.integration;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.restdocs.RestDocumentation.document;
import static org.springframework.restdocs.RestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Persistable;
import org.springframework.http.MediaType;
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
import org.wellspring.crud.controller.RestCrudController;
import org.wellspring.crud.persistence.repository.CrudRepository;
import org.wellspring.crud.service.CrudService;
import org.wellspring.crud.util.CrudConstants;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;

import junit.framework.TestCase;

@ComponentScan("org.wellspring.crud")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfiguration.class })
@WebAppConfiguration
@Transactional
@TransactionConfiguration(defaultRollback = true)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public abstract class RestCrudTest<C extends RestCrudController<S, R, T, ID>, S extends CrudService<R, T, ID>, R extends CrudRepository<T, ID>, T extends Persistable<ID>, ID extends Serializable>
		extends TestCase {

	private static final Logger LOGGER = LoggerFactory.getLogger(RestCrudTest.class);

	private Class<T> entityClass;

	private Class<C> controllerClass;

	abstract Map<ID, Persistable<ID>> getEntities();

	private String basicMapping;

	ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).configure(SerializationFeature.INDENT_OUTPUT, true).registerModule(new JodaModule());

	protected MockMvc mockMvc;

	Map<ID, Persistable<ID>> entities = new HashMap<ID, Persistable<ID>>();

	@Autowired
	private WebApplicationContext wac;

	T getRawObject(final MvcResult mvcResult, Class<T> clazz) throws Exception {
		return mapper.readValue(mvcResult.getResponse()
				.getContentAsString(), clazz);
	}

	T getRawObject(final MvcResult mvcResult) throws Exception {
		return mapper.readValue(mvcResult.getResponse()
				.getContentAsString(), entityClass);
	}

	public static byte[] convertObjectToJsonBytes(Object object)
			throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		return mapper.writeValueAsBytes(object);
	}

	@SuppressWarnings("unchecked")
	@Before
	public void setup() throws JsonProcessingException, Exception {

		mockMvc = MockMvcBuilders.webAppContextSetup(wac)
				.apply(documentationConfiguration())
				.build();

		entityClass = (Class<T>) ((ParameterizedType) this.getClass()
				.getGenericSuperclass()).getActualTypeArguments()[3];

		controllerClass = (Class<C>) ((ParameterizedType) this.getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];

		RequestMapping requestMapping = controllerClass.getAnnotation(RequestMapping.class);

		assertNotNull("requestMapping expected", requestMapping);

		assertNotNull("requestMapping url expected", requestMapping.value());

		basicMapping = requestMapping.value()[0];

		assertNotNull("basicMapping expected", basicMapping);
		saveData();
	}

	private void saveData()
			throws JsonProcessingException, Exception {

		MvcResult mvcResult;
		for (Persistable<ID> persistable : getEntities().values()) {
			mvcResult = mockMvc
					.perform(
							post(basicMapping + CrudConstants.OPERATION_SAVE)
									.contentType(MediaType.APPLICATION_JSON)
									.content(mapper.writeValueAsString(persistable)))
					.andDo(print())
					.andDo(document(basicMapping + "/post"))
					.andExpect(status().isCreated())

			.andExpect((jsonPath("$.id", notNullValue())))
					.andReturn();
			persistable = getRawObject(mvcResult);
			entities.put(persistable.getId(), persistable);
		}
	}

	// @Test
	// public void testRestDoc() throws Exception {
	// this.mockMvc.perform(get("/").accept(MediaType.APPLICATION_JSON))
	// .andExpect(status().isOk())
	// .andDo(document("index"));
	// }

	@Test
	public void aEntitiesSizeTest() {
		assertFalse("entities size zero", getEntities().isEmpty());
	}

	@Test
	public void bUpdateTest() throws Exception {

		try {

			for (Persistable<ID> persistable : entities.values()) {

				mockMvc
						.perform(
								put(basicMapping + CrudConstants.OPERATION_UPDATE + "/" + persistable.getId())
										.contentType(MediaType.APPLICATION_JSON)
										.content(mapper.writeValueAsString(persistable)))
						.andDo(print())
						.andDo(document(basicMapping + "/put"))
						.andExpect(status().isOk()) // saved again

				.andExpect((jsonPath("$.id", notNullValue())))
						.andReturn();

			}

		} catch (Exception e) {
			LOGGER.error("fail", e);
			throw e;
		}

	}

	@Test
	public void cCountTest()
			throws JsonProcessingException, Exception {
		try {

			mockMvc
					.perform(
							get(basicMapping + CrudConstants.OPERATION_COUNT))
					.andDo(print())
					.andDo(document(basicMapping + CrudConstants.OPERATION_COUNT))
					.andExpect(status().isOk())
					.andExpect((jsonPath("$.content", isA(Integer.class))))
					.andExpect((jsonPath("$", notNullValue())))
					.andReturn();

		} catch (Exception e) {
			LOGGER.error("fail", e);
			throw e;
		}

	}

	@Test
	public void dFindOneTest() throws Exception {

		try {

			for (Persistable<ID> persistable : entities.values()) {
				mockMvc
						.perform(
								get(
										basicMapping + CrudConstants.OPERATION_FIND_ONE
												+ "/" + persistable.getId()))
						.andDo(print())
						.andDo(document(basicMapping + "/get"))
						.andExpect(status().isOk())

				.andExpect((jsonPath("$.id", notNullValue())))

				// .andExpect((jsonPath("$.id").value(persistable.getId())))

				.andReturn();
			}

		} catch (Exception e) {
			LOGGER.error("fail", e);
			throw e;
		}

	}

	@Test
	public void existsTest()
			throws JsonProcessingException, Exception {
		try {
			for (Persistable<ID> persistable : entities.values()) {
				mockMvc
						.perform(
								get(
										basicMapping + CrudConstants.OPERATION_EXISTS
												+ "/" + persistable.getId()))
						.andDo(print())
						.andDo(document(basicMapping + CrudConstants.OPERATION_EXISTS))
						.andExpect(status().isOk())

				.andExpect((jsonPath("$", notNullValue())))

				.andReturn();
			}
		} catch (Exception e) {
			LOGGER.error("fail", e);
			throw e;
		}
	}

	@Test
	public synchronized void fDeleteByIdTest() throws Exception {
		try {
			for (Persistable<ID> persistable : entities.values()) {
				mockMvc
						.perform(
								delete(
										basicMapping + CrudConstants.OPERATION_DELETE_BY_ID
												+ "/" + persistable.getId()))
						.andDo(print())
						.andDo(document(basicMapping + CrudConstants.OPERATION_DELETE_BY_ID))
						.andExpect(status().isOk())

				.andExpect((jsonPath("$", notNullValue())))

				.andReturn();
			}
		} catch (Exception e) {
			LOGGER.error("fail", e);
			throw e;
		}
	}

	@Test
	public synchronized void gDeleteByEntityTest() throws Exception {
		try {
			for (Persistable<ID> persistable : entities.values()) {
				mockMvc
						.perform(
								delete(
										basicMapping
												+ CrudConstants.OPERATION_DELETE_BY_ENTITY)
														.contentType(MediaType.APPLICATION_JSON)
														.content(mapper.writeValueAsString(persistable)))
						.andDo(print())
						.andDo(document(basicMapping
								+ CrudConstants.OPERATION_DELETE_BY_ENTITY))
						.andExpect(status().isOk())

				.andExpect((jsonPath("$", notNullValue())))

				.andReturn();
			}
		} catch (Exception e) {
			LOGGER.error("fail", e);
			throw e;
		}
	}

	@Test
	public synchronized void hDeleteAllTest() throws Exception {
		try {

			mockMvc
					.perform(
							delete(basicMapping + CrudConstants.OPERATION_DELETE_ALL))
					.andDo(print())
					.andDo(document(basicMapping + CrudConstants.OPERATION_DELETE_ALL))
					.andExpect(status().isOk())

			.andExpect((jsonPath("$", notNullValue())))

			.andReturn();

		} catch (Exception e) {
			LOGGER.error("fail", e);
			throw e;
		}
	}

	@Test
	public synchronized void iValidateTest() throws Exception {
		try {
			for (Persistable<ID> persistable : entities.values()) {
				mockMvc
						.perform(
								post(
										basicMapping
												+ CrudConstants.OPERATION_VALIDATE)
														.contentType(MediaType.APPLICATION_JSON)
														.content(mapper.writeValueAsString(persistable)))
						.andDo(print())
						.andDo(document(basicMapping + CrudConstants.OPERATION_VALIDATE))
						.andExpect(status().isOk())

				.andExpect((jsonPath("$", notNullValue())))
						.andExpect((jsonPath("$.content", isA(Boolean.class))))
						.andExpect((jsonPath("$.content", is(true))))

				.andReturn();
			}
		} catch (Exception e) {
			LOGGER.error("fail", e);
			throw e;
		}
	}

	@Test
	public synchronized void jValidateErrorTest() throws Exception {
		try {
			Persistable<ID> persistable = entityClass.newInstance();
			mockMvc
					.perform(
							post(
									basicMapping
											+ CrudConstants.OPERATION_VALIDATE)
													.contentType(MediaType.APPLICATION_JSON)
													.content(mapper.writeValueAsString(persistable)))
					.andDo(print())
					.andDo(document(basicMapping + CrudConstants.OPERATION_VALIDATE))

			.andExpect(status().isUnprocessableEntity())
					.andExpect((jsonPath("$", notNullValue()))).andDo(print())
					.andExpect((jsonPath("$.code", notNullValue())))
					.andExpect((jsonPath("$.message", notNullValue())))
					.andReturn();

		} catch (Exception e) {
			LOGGER.error("fail", e);
			throw e;
		}
	}

	@Test
	public void kQuery()
			throws JsonProcessingException, Exception {
		try {

			mockMvc
					.perform(
							get(basicMapping + CrudConstants.OPERATION_QUERY))
					.andDo(print())
					.andDo(document(basicMapping + CrudConstants.OPERATION_QUERY))
					.andExpect(status().isOk())
					// .andExpect((jsonPath("$.content", isA(Integer.class))))
					.andExpect((jsonPath("$", notNullValue())))
					.andReturn();

		} catch (Exception e) {
			LOGGER.error("fail", e);
			throw e;
		}

	}

}
