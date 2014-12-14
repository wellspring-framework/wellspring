package org.wellspring.example.angular.backend.util;

public class PackageConstants {
	public static final String BASE_PACKAGE = "org.wellspring.example.angular.backend";
	public static final String PERSISTENCE_PACKAGE = BASE_PACKAGE + ".persistence";
	public static final String CONFIG_PACKAGE = BASE_PACKAGE + ".config";
	public static final String CONFIG_SECURITY_PACKAGE = CONFIG_PACKAGE + ".security";
	public static final String CONFIG_SECURITY_ADAPTER_PACKAGE = CONFIG_SECURITY_PACKAGE + ".adapter";
	public static final String CONTROLLER_PACKAGE = BASE_PACKAGE + ".controller";
	public static final String DOMAIN_PACKAGE = PERSISTENCE_PACKAGE + ".domain";
	public static final String REPOSITORY_PACKAGE = PERSISTENCE_PACKAGE + ".repository";
	public static final String SOLR_REPOSITORY_PACKAGE = PERSISTENCE_PACKAGE + ".solr.repository";
	public static final String REPOSITORY_IMPL_PACKAGE = REPOSITORY_PACKAGE + ".impl";
	public static final String SERVICE_PACKAGE = BASE_PACKAGE + ".service";
}
