package org.wellspring.config.webapp;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

public abstract class InternalResourceViewResolverConfig extends WebMvcConfigurerAdapter {

	@Bean
	public ViewResolver configureInternalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix(getPrefix());
		resolver.setSuffix(getSuffix());
		// resolver.setViewClass(JstlView.class);
		// resolver.setExposedContextBeanNames("locale", "domainUtil");
		// resolver.setOrder(1);
		return resolver;
	}

	public abstract String getPrefix();

	public abstract String getSuffix();
}
