package org.wellspring.example.angular.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.wellspring.config.jackson.JacksonConfig;
import org.wellspring.crud.util.CrudPackageConstants;
import org.wellspring.example.angular.backend.util.PackageConstants;

@Configuration
@EnableWebMvc
@EnableSpringDataWebSupport
@EnableJpaRepositories
@EnableAutoConfiguration
@PropertySource("application.properties")
@Import(JacksonConfig.class)
@ComponentScan(basePackages = { PackageConstants.BASE_PACKAGE, CrudPackageConstants.CONTROLLER_PACKAGE })
public class Application {

	public static void main(String[] args) {
		String webPort = System.getenv("PORT");
		if (webPort == null || webPort.isEmpty()) {
			webPort = "8080";
		}
		System.setProperty("server.port", webPort);
		SpringApplication.run(Application.class, args);
	}
}