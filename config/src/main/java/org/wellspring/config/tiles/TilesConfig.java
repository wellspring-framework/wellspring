package org.wellspring.config.tiles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

@Configuration
public class TilesConfig {

	public static final Logger LOGGER = LoggerFactory.getLogger(TilesConfig.class);

	public TilesConfig() {
		LOGGER.info("Configuracao criada com sucesso.");
	}

	@Bean
	public org.springframework.web.servlet.view.UrlBasedViewResolver tilesViewResolver() {
		org.springframework.web.servlet.view.UrlBasedViewResolver urlBasedViewResolver = new UrlBasedViewResolver();
		urlBasedViewResolver.setViewClass(TilesView.class);
		return urlBasedViewResolver;
	}

	@Bean
	public org.springframework.web.servlet.view.tiles3.TilesConfigurer tilesConfigurer() {
		TilesConfigurer tilesConfigurer = new TilesConfigurer();
		tilesConfigurer.setDefinitions("/WEB-INF/tiles-defs/templates.xml");
		return tilesConfigurer;
	}

}
