package org.wellspring.crud.config;

import org.springframework.context.annotation.Configuration;
import org.wellspring.config.jpa.JPAConfiguration;
import org.wellspring.crud.util.CrudPackageConstants;

@Configuration
public class CrudJPAConfiguration extends JPAConfiguration {

	@Override
	public String getPackagesToScan() {
		return CrudPackageConstants.DOMAIN_PACKAGE;
	}
	/*
	 * @Override
	 * public String[] getPackagesToScan() {
	 * String packages[] = { PackageConstants.DOMAIN_PACKAGE,
	 * PackageConstants.REPOSITORY_IMPL_PACKAGE };
	 * return packages;
	 * }
	 */
}
