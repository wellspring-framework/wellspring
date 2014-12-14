package org.wellspring.example.angular.backend.util;

public class ResourcePaths {
	public static final String ROOT_API = "/api";

	public static final String VAR_SEARCH_BY_TERM = "/{searchTerm}";

	public static final String VAR_PAGE_INDEX = "/{pageIndex}";

	public static final String VAR_PAGE_SIZE = "/{pageSize}";

	public static final String VAR_SORTED_BY = "/{sortedBy}";

	public static final String PUBLIC_ROOT_API = ROOT_API + "/public";

	public static final String PRIVATE_ROOT_API = ROOT_API + "/private";

	public class Product {
		public static final String ROOT = "/products";
		public static final String SEARCH_BY_TERM = "/searchByTerm";
	}

	public class User {
		public static final String ROOT = "/users";
		public static final String SEARCH_BY_TERM = "/searchByTerm";
	}

}