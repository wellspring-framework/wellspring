package org.wellspring.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.CompositePropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.ResourcePropertySource;

public class EnvironmentUtils {
	public static final Logger LOGGER = LoggerFactory.getLogger(EnvironmentUtils.class);

	public static Map<String, Object> getEnvAsMap(Environment environment) {
		Map<String, Object> map = new HashMap<String, Object>();
		for (Iterator<PropertySource<?>> it = ((AbstractEnvironment) environment).getPropertySources().iterator(); it.hasNext();) {
			PropertySource<?> propertySource = it.next();
			if (propertySource instanceof CompositePropertySource) {
				for (Iterator<PropertySource<?>> it2 = ((CompositePropertySource) propertySource).getPropertySources().iterator(); it2.hasNext();) {
					PropertySource<?> propertySource2 = it2.next();
					if (propertySource2 instanceof ResourcePropertySource) {
						for (Entry<String, Object> entry : ((ResourcePropertySource) propertySource2).getSource().entrySet()) {
							if (entry.getValue() instanceof String) {
								LOGGER.debug("Adding property " + entry.getKey());
								map.put(entry.getKey(), entry.getValue());
							}
						}
					}
				}
			}
		}
		return map;
	}
}
