package org.wellspring.crud.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.wellspring.config.datasource.HsqlDataSourceConfig;
import org.wellspring.config.jackson.JacksonConfig;
import org.wellspring.crud.util.CrudPackageConstants;

@Configuration
@EnableWebMvc
@EnableSpringDataWebSupport
@ComponentScan(basePackages = { CrudPackageConstants.CONFIG_PACKAGE,
		CrudPackageConstants.REPOSITORY_IMPL_PACKAGE,
		CrudPackageConstants.DOMAIN_PACKAGE, CrudPackageConstants.SERVICE_PACKAGE,
		CrudPackageConstants.CONTROLLER_PACKAGE })
@EnableJpaRepositories(basePackages = {
		CrudPackageConstants.REPOSITORY_IMPL_PACKAGE }, includeFilters = @ComponentScan.Filter(value = {}, type = FilterType.ASSIGNABLE_TYPE) )
@EnableTransactionManagement
@Import({ WebInitializer.class, DispatcherConfig.class,
		HsqlDataSourceConfig.class, CrudJPAConfiguration.class, JacksonConfig.class })
public class AppConfiguration {

}
