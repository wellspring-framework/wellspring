package org.wellspring.config.webapp;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ViewResolverConfig extends InternalResourceViewResolverConfig {

	public static final Logger LOGGER = LoggerFactory.getLogger(ViewResolverConfig.class);

	@PostConstruct
	public void logMessage() {
		LOGGER.info("Configuration created..");
	}

	@Override
	public String getPrefix() {

		return "/WEB-INF/views/";
	}

	@Override
	public String getSuffix() {

		return ".jsp";
	}

}
