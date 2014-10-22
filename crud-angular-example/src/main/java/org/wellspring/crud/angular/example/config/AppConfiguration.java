package org.wellspring.crud.angular.example.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.wellspring.config.datasource.HsqlDataSourceConfig;
import org.wellspring.config.tiles.TilesConfig;
import org.wellspring.crud.angular.example.util.PackageConstants;

@Configuration
@EnableWebMvc
@EnableSpringDataWebSupport
@ComponentScan(basePackages = { PackageConstants.BASE_PACKAGE })
@EnableJpaRepositories(basePackages = {
		PackageConstants.BASE_PACKAGE }, includeFilters = @ComponentScan.Filter(value = {}, type = FilterType.ASSIGNABLE_TYPE))
@EnableTransactionManagement
@Import({ DispatcherConfig.class,
		HsqlDataSourceConfig.class,
		CrudJPAConfiguration.class, ViewResolverConfig.class, TilesConfig.class, WebAppConfig.class })
public class AppConfiguration {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(AppConfiguration.class);

	public AppConfiguration() {
		LOGGER.info("App started");
	}

}