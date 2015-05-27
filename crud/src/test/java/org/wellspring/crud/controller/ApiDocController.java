package org.wellspring.crud.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.wellspring.crud.util.ResourcePaths;

@RestController
@RequestMapping(ResourcePaths.ApiDoc.ROOT)
public class ApiDocController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ApiDocController.class);

	@Autowired
	private ApplicationContext appContext;

	/**
	 * // TODO find classes with @RestController // TODO find GET methods
	 */
	// @PostConstruct
	public void init() {
		Map<String, Object> controllers = appContext.getBeansWithAnnotation(RestController.class);

		for (Iterator<Object> iterator = controllers.values().iterator(); iterator.hasNext();) {
			Object controller = iterator.next();
			LOGGER.info(controller.getClass().getName());
			RequestMapping requestMappingController = (RequestMapping) controller.getClass()
					.getAnnotation(RequestMapping.class);

			if (requestMappingController != null) {
				LOGGER.info("base url: {}", requestMappingController.value());
			}

			for (Method method : controller.getClass().getMethods()) {
				RequestMapping requestMapping = (RequestMapping) method.getAnnotation(RequestMapping.class);
				if (requestMapping != null) {
					LOGGER.info("name: {}\t method:{}\t consumes:{}\t produces:{}\t params:{}\t", requestMapping.name(),
							requestMapping.method(), requestMapping.consumes(), requestMapping.produces(),
							requestMapping.params());
				}

				Parameter[] parameters = method.getParameters();

				for (int i = 0; i < parameters.length; i++) {
					LOGGER.info("params:{}\t", parameters[i].getName());
				}
			}
		}

	}

	@RequestMapping(value = "index", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public Resources<Link> index() {
		List<Link> links = new ArrayList<Link>();
		links.add(buildLink(ResourcePaths.City.ROOT, "get"));
		Resources<Link> resource = new Resources<Link>(links);
		return resource;
	}

	private Link buildLink(String path, String rel) {
		return new Link(linkTo(getClass()).toUriComponentsBuilder().path(path).buildAndExpand().toUriString()).withRel(rel);
	}

}