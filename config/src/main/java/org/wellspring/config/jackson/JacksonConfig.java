package org.wellspring.config.jackson;

import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.datatype.joda.JodaModule;

public class JacksonConfig extends WebMvcConfigurerAdapter {
	public static final Logger LOGGER = LoggerFactory.getLogger(JacksonConfig.class);

	/*
	 * @Bean
	 * Jackson2ObjectMapperFactoryBean objectMapper() {
	 * Jackson2ObjectMapperFactoryBean jackson2ObjectMapperFactoryBean = new Jackson2ObjectMapperFactoryBean();
	 * jackson2ObjectMapperFactoryBean.getObject();
	 * ObjectMapper objectMapper = new ObjectMapper();
	 * objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
	 * .configure(SerializationFeature.INDENT_OUTPUT, true)
	 * .registerModule(new JodaModule());
	 * return jackson2ObjectMapperFactoryBean;
	 * }
	 * @Bean
	 * public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
	 * MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
	 * mappingJackson2HttpMessageConverter.setObjectMapper(objectMapper=3());
	 * return mappingJackson2HttpMessageConverter;
	 * }
	 * @Override
	 * public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
	 * super.configureMessageConverters(converters);
	 * converters.add(mappingJackson2HttpMessageConverter());
	 * }
	 */

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(new MappingJackson2HttpMessageConverter(jacksonBuilder().build()));
	}

	@Bean
	public Jackson2ObjectMapperBuilder jacksonBuilder() {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
		builder.indentOutput(true);
		builder.failOnUnknownProperties(false);
		builder.modulesToInstall(new JodaModule());
		return builder;
	}

	@PostConstruct
	public void setup() {
		LOGGER.info("Configuration created..");
	}
}