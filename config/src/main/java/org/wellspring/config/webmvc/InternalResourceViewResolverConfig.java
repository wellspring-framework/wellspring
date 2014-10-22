package org.wellspring.config.webmvc;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

public abstract class InternalResourceViewResolverConfig {
	@Bean
	public InternalResourceViewResolver configureInternalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix(getPrefix());
		resolver.setSuffix(getSuffix());
		return resolver;
	}

	public abstract String getPrefix();

	public abstract String getSuffix();
}
