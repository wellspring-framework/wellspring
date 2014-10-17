package org.wellspring.crud.test.integration;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.wellspring.crud.config.AppConfiguration;
import org.wellspring.crud.config.JPAConfiguration;
import org.wellspring.crud.persistence.domain.User;

import com.fasterxml.jackson.databind.ObjectMapper;

@ComponentScan("org.wellspring.crud")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfiguration.class,
		JPAConfiguration.class })
@WebAppConfiguration
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class UserControllerIntegrationTests {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(UserControllerIntegrationTests.class);

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void testSaveS() throws Exception {
		try {
			User user = new User();
			user.setId(1L);
			user.setName("User 1");
			LOGGER.info("Test started...");
			mockMvc.perform(
					get("/user/save").contentType(MediaType.APPLICATION_JSON)
							.content(
									new ObjectMapper()
											.writeValueAsString(user)))
					.andExpect(status().isOk())
					// .andExpect(jsonPath("$.exception", nullValue()))
					// .andExpect(jsonPath("$.errors", hasSize(0)))
					// .andExpect((jsonPath("$.ssoToken", notNullValue())))
					.andDo(print()).andReturn();

			assertTrue("Sucessfull Test", true);
			LOGGER.info("Test finished.");
		} catch (Exception e) {
			LOGGER.error("FAIL!", e);
			throw e;
		}
	}

}
