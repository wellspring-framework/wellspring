package org.wellspring.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wellspring.crud.controller.impl.AbstractRestCrudController;
import org.wellspring.crud.persistence.domain.User;
import org.wellspring.crud.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController extends AbstractRestCrudController<User, Long> {
	@Autowired
	public UserController(UserService crudService) {
		super(crudService);
	}

}
