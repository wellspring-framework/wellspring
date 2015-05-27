package org.wellspring.crud.test.integration;

import static org.springframework.restdocs.RestDocumentation.document;
import static org.springframework.restdocs.RestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
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
import org.wellspring.crud.util.ResourcePaths;

import com.fasterxml.jackson.core.JsonProcessingException;

import junit.framework.TestCase;

@ComponentScan("org.wellspring.crud")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfiguration.class })
@WebAppConfiguration
@Transactional
@TransactionConfiguration(defaultRollback = true)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ApiDocIntegrationTests extends TestCase {
	private static final Logger LOGGER = LoggerFactory.getLogger(ApiDocIntegrationTests.class);

	protected MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setup() throws JsonProcessingException, Exception {

		mockMvc = MockMvcBuilders.webAppContextSetup(wac)
				.apply(documentationConfiguration())
				.build();
	}

	@Test
	public void api() throws Exception {
		this.mockMvc.perform(get(ResourcePaths.ApiDoc.INDEX).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(document(""));
	}
}
