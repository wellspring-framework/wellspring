package org.wellspring.crud.config;

import org.springframework.context.annotation.Configuration;
import org.wellspring.config.jpa.JPAConfiguration;
import org.wellspring.crud.util.PackageConstants;

@Configuration
public class CrudJPAConfiguration extends JPAConfiguration {

	@Override
	public String getPackagesToScan() {
		return PackageConstants.DOMAIN_PACKAGE;
	}

}
