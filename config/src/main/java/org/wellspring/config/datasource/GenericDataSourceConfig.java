package org.wellspring.config.datasource;

import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@PropertySource({ "classpath:/datasource.properties" })
public class GenericDataSourceConfig {
	@Autowired
	private Environment environment;

	public static final Logger LOGGER = LoggerFactory.getLogger(GenericDataSourceConfig.class);

	@PostConstruct
	public void logMessage() {
		LOGGER.info("Configuration created..");
	}

	@Bean
	public DataSource dataSource() throws SQLException {

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment
				.getProperty("jdbc.driverClassName"));
		dataSource.setUrl(environment.getProperty("jdbc.url"));
		dataSource.setUsername(environment.getProperty("jdbc.user"));
		dataSource.setPassword(environment.getProperty("jdbc.pass"));
		return dataSource;
	}
}
