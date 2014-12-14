package org.wellspring.config.datasource;

import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

public class DerbyDataSourceConfig {

	public static final Logger LOGGER = LoggerFactory.getLogger(DerbyDataSourceConfig.class);

	@PostConstruct
	public void logMessage() {
		LOGGER.info("Configuration created..");
	}

	@Bean
	public DataSource dataSource() throws SQLException {

		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		return builder.setType(EmbeddedDatabaseType.DERBY).build();
	}
}
