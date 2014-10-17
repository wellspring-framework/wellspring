package org.wellspring.crud.test.integration;

import static org.wellspring.crud.persistence.assertions.JPAAssertions.assertTableExists;
import static org.wellspring.crud.persistence.assertions.JPAAssertions.assertTableHasColumn;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { org.wellspring.crud.config.JPAConfiguration.class })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class UserMappingIntegrationTests {

	@Autowired
	@Qualifier(value = "entityManager")
	EntityManager manager;

	@Test
	public void thatItemCustomMappingWorks() throws Exception {
		assertTableExists(manager, "user");
		assertTableHasColumn(manager, "user", "id");
	}

}