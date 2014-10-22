package org.wellspring.crud.angular.example.config;

import org.springframework.context.annotation.Configuration;
import org.wellspring.config.jpa.JPAConfiguration;
import org.wellspring.crud.angular.example.util.PackageConstants;

@Configuration
public class CrudJPAConfiguration extends JPAConfiguration {

	@Override
	public String getPackagesToScan() {
		return PackageConstants.DOMAIN_PACKAGE;
	}

}
