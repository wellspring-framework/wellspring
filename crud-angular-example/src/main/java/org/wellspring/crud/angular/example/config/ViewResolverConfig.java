package org.wellspring.crud.angular.example.config;

import org.springframework.context.annotation.Configuration;
import org.wellspring.config.webmvc.InternalResourceViewResolverConfig;

@Configuration
public class ViewResolverConfig extends InternalResourceViewResolverConfig {
	@Override
	public String getPrefix() {

		return "/WEB-INF/pages/";
	}

	@Override
	public String getSuffix() {

		return ".jsp";
	}

}
