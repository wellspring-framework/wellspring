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
import org.wellspring.crud.util.PackageConstants;

@Configuration
@EnableWebMvc
@EnableSpringDataWebSupport
@ComponentScan(basePackages = { PackageConstants.CONFIG_PACKAGE,
		PackageConstants.REPOSITORY_IMPL_PACKAGE,
		PackageConstants.DOMAIN_PACKAGE, PackageConstants.SERVICE_PACKAGE,
		PackageConstants.CONTROLLER_PACKAGE })
@EnableJpaRepositories(basePackages = {
		PackageConstants.REPOSITORY_IMPL_PACKAGE,
		PackageConstants.DOMAIN_PACKAGE }, includeFilters = @ComponentScan.Filter(value = {}, type = FilterType.ASSIGNABLE_TYPE))
@EnableTransactionManagement
@Import({ WebInitializer.class, DispatcherConfig.class,
		HsqlDataSourceConfig.class, CrudJPAConfiguration.class })
public class AppConfiguration {

}
