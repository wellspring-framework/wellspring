package org.wellspring.config.jpa;

import java.sql.SQLException;
import java.util.Properties;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@PropertySource({ "classpath:/hibernate.properties" })
public abstract class JPAConfiguration {
	public static final Logger LOGGER = LoggerFactory
			.getLogger(JPAConfiguration.class);
	@Resource
	javax.sql.DataSource dataSource;

	@Autowired
	private Environment environment;

	public JPAConfiguration() {
		LOGGER.info("Configuracao criada com sucesso.");
	}

	Properties hibernateProperties() {
		return new Properties() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			{
				put("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto"));
				put("hibernate.dialect", environment.getProperty("hibernate.dialect"));
				// put("hibernate.globally_quoted_identifiers",
				// environment.getProperty("hibernate.globally_quoted_identifiers"));
				put("hibernate.hbm2ddl.import_files", environment.getProperty("hibernate.hbm2ddl.import_files"));
				put("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));

			}
		};
	}

	@Bean
	public EntityManagerFactory entityManagerFactory() throws SQLException {

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(true);
		vendorAdapter.setShowSql(true);
		vendorAdapter.setDatabasePlatform(environment
				.getProperty("hibernate.dialect"));

		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		// TODO to resolve
		factory.setPackagesToScan(getPackagesToScan());
		factory.setDataSource(dataSource);
		factory.afterPropertiesSet();
		factory.setJpaProperties(hibernateProperties());

		return factory.getObject();
	}

	@Bean
	@Qualifier(value = "entityManager")
	public EntityManager entityManager(EntityManagerFactory entityManagerFactory) {
		return entityManagerFactory.createEntityManager();
	}

	@Bean
	public PlatformTransactionManager transactionManager() throws SQLException {

		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(entityManagerFactory());
		return txManager;
	}

	@Bean
	public HibernateExceptionTranslator hibernateExceptionTranslator() {
		return new HibernateExceptionTranslator();
	}

	public abstract String getPackagesToScan();

}