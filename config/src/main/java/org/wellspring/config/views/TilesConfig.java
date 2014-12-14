package org.wellspring.config.views;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

@Configuration
@EnableWebMvc
public class TilesConfig {

	public static final Logger LOGGER = LoggerFactory.getLogger(TilesConfig.class);

	@PostConstruct
	public void logMessage() {
		LOGGER.info("Configuration created..");
	}

	/*
	 * private Map<String, MediaType> mappings() {
	 * Map<String, MediaType> mappings = new HashMap<String, MediaType>();
	 * mappings.put("html", MediaType.TEXT_HTML);
	 * mappings.put("pdf", MediaType.valueOf("application/pdf"));
	 * mappings.put("xsl", MediaType.valueOf("application/vnd.ms-excel"));
	 * mappings.put("xml", MediaType.TEXT_XML);
	 * mappings.put("json", MediaType.APPLICATION_JSON);
	 * mappings.put("atom", MediaType.APPLICATION_XML);
	 * return mappings;
	 * }
	 * 
	 * public PathExtensionContentNegotiationStrategy
	 * pathExtensionContentNegotiationStrategy() {
	 * PathExtensionContentNegotiationStrategy
	 * pathExtensionContentNegotiationStrategy = new
	 * PathExtensionContentNegotiationStrategy(mappings());
	 * return pathExtensionContentNegotiationStrategy;
	 * 
	 * }
	 * 
	 * public ContentNegotiationManager contentNegotiationManager() {
	 * ContentNegotiationManager contentNegotiationManager = new
	 * ContentNegotiationManager(pathExtensionContentNegotiationStrategy());
	 * return contentNegotiationManager;
	 * }
	 * 
	 * @Bean
	 * public ContentNegotiatingViewResolver contentNegotiatingResolver() {
	 * ContentNegotiatingViewResolver contentNegotiatingViewResolver = new
	 * ContentNegotiatingViewResolver();
	 * contentNegotiatingViewResolver.setOrder(org.springframework.core.Ordered.
	 * HIGHEST_PRECEDENCE);
	 * contentNegotiatingViewResolver.setContentNegotiationManager(
	 * contentNegotiationManager());
	 * return contentNegotiatingViewResolver;
	 * }
	 */

	/*
	 * @Bean
	 * public InternalResourceViewResolver getInternalResourceViewResolver() {
	 * InternalResourceViewResolver resolver = new
	 * InternalResourceViewResolver();
	 * resolver.setPrefix("/WEB-INF/views/");
	 * resolver.setSuffix(".jsp");
	 * resolver.setOrder(1);
	 * return resolver;
	 * }
	 */
	/*
	 * @Bean
	 * public ResourceBundleViewResolver viewResolver() {
	 * ResourceBundleViewResolver resourceBundleViewResolver = new
	 * ResourceBundleViewResolver();
	 * resourceBundleViewResolver.setBasename("/views/");
	 * resourceBundleViewResolver.setOrder(tilesViewResolver().getOrder() + 1);
	 * return resourceBundleViewResolver;
	 * }
	 */

	/*
	 * @Bean
	 * public org.springframework.web.servlet.view.UrlBasedViewResolver
	 * viewResolver() {
	 * org.springframework.web.servlet.view.UrlBasedViewResolver
	 * urlBasedViewResolver = new UrlBasedViewResolver();
	 * urlBasedViewResolver.set
	 * urlBasedViewResolver.setOrder(contentNegotiatingResolver().getOrder() +
	 * 1);
	 * return urlBasedViewResolver;
	 * }
	 */

	@Bean
	public org.springframework.web.servlet.view.UrlBasedViewResolver viewResolver() {
		TilesViewResolver resolver = new TilesViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		resolver.setViewClass(TilesView.class);
		return resolver;
	}

	@Bean
	public org.springframework.web.servlet.view.tiles3.TilesConfigurer tilesConfigurer() {
		TilesConfigurer tilesConfigurer = new TilesConfigurer();
		tilesConfigurer.setCompleteAutoload(true);
		tilesConfigurer.setCheckRefresh(true);
		tilesConfigurer.setDefinitions("/WEB-INF/tiles-defs/tiles-templates.xml");
		return tilesConfigurer;
	}

	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
		commonsMultipartResolver.setMaxUploadSize(1000000);
		return commonsMultipartResolver;
	}

}
