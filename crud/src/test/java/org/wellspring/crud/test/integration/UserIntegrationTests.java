package org.wellspring.crud.test.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.wellspring.crud.persistence.assertions.JPAAssertions.assertTableExists;
import static org.wellspring.crud.persistence.assertions.JPAAssertions.assertTableHasColumn;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.springframework.data.domain.Persistable;
import org.springframework.http.MediaType;
import org.wellspring.crud.controller.UserController;
import org.wellspring.crud.persistence.domain.User;
import org.wellspring.crud.persistence.repository.impl.UserRepository;
import org.wellspring.crud.service.UserService;
import org.wellspring.crud.util.ResourcePaths;

public class UserIntegrationTests extends RestCrudTest<UserController, UserService, UserRepository, User, Long> {

	@PersistenceContext
	private EntityManager manager;

	@Override
	Map<Long, Persistable<Long>> getEntities() {
		Map<Long, Persistable<Long>> users = new HashMap<Long, Persistable<Long>>();
		User user1 = new User();
		User user2 = new User();
		User user3 = new User();

		user1.setName("John Jones");
		user1.setLastName("Silva");
		user1.setEmail("john.jones@test.com");

		user2.setName("Mary Jones");
		user2.setLastName("Silva");
		user2.setEmail("mary.jones@test.com");

		user3.setName("Joseph Jones");
		user3.setLastName("Silva");
		user3.setEmail("joseph.jones@test.com");

		users.put(1L, user1);
		users.put(2L, user2);
		users.put(3L, user3);

		return users;
	}

	@Test
	public void searchByTermTest() throws Exception {
		mockMvc
				.perform(
						get(
								ResourcePaths.User.ROOT + ResourcePaths.URL_FIND_SEARCH_TERM + "/John").contentType(
										MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())

		.andDo(print()).andReturn();
	}

	@Test
	public void thatItemCustomMappingWorks() throws Exception {
		assertTableExists(manager, "user");
		assertTableHasColumn(manager, "user", "id");
	}

}
