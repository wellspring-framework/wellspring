package org.wellspring.example.angular.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wellspring.crud.controller.impl.RestCrudControllerImpl;
import org.wellspring.example.angular.backend.persistence.domain.User;
import org.wellspring.example.angular.backend.persistence.repository.UserRepository;
import org.wellspring.example.angular.backend.service.UserService;
import org.wellspring.example.angular.backend.util.ResourcePaths;

@RestController
@RequestMapping(value = ResourcePaths.PUBLIC_ROOT_API + ResourcePaths.User.ROOT)
public class UserController extends RestCrudControllerImpl<UserService, UserRepository, User, Long> {

}
