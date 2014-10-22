package org.wellspring.crud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wellspring.crud.controller.impl.rest.RestCrudControllerImpl;
import org.wellspring.crud.persistence.domain.User;
import org.wellspring.crud.persistence.repository.impl.UserRepository;
import org.wellspring.crud.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController extends
		RestCrudControllerImpl<UserService, UserRepository, User, Long> {

}
