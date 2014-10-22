package org.wellspring.crud.angular.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wellspring.crud.angular.example.persistence.domain.User;
import org.wellspring.crud.angular.example.persistence.repository.UserRepository;
import org.wellspring.crud.angular.example.service.UserService;
import org.wellspring.crud.controller.impl.rest.RestCrudControllerImpl;

@Controller
@RequestMapping("/user")
public class UserController extends
		RestCrudControllerImpl<UserService, UserRepository, User, Long> {

}
