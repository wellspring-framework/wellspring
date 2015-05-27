package org.wellspring.crud.controller;

import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.wellspring.crud.controller.impl.RestCrudControllerImpl;
import org.wellspring.crud.persistence.domain.User;
import org.wellspring.crud.persistence.repository.impl.UserRepository;
import org.wellspring.crud.service.UserService;
import org.wellspring.crud.util.ResourcePaths;

@RestController
@RequestMapping(ResourcePaths.User.ROOT)
public class UserController extends
		RestCrudControllerImpl<UserService, UserRepository, User, Long> {

	@RequestMapping(value = ResourcePaths.URL_FIND_SEARCH_TERM + ResourcePaths.VAR_SEARCH_BY_TERM, method = RequestMethod.GET, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public HttpEntity<Resources<User>> findBySearchTerm(@PathVariable("searchTerm") String searchTerm) {
		Resources<User> resource = new Resources<User>(getService().findBySearchTerm(searchTerm));
		return new ResponseEntity<Resources<User>>(resource, HttpStatus.OK);
	}

}
