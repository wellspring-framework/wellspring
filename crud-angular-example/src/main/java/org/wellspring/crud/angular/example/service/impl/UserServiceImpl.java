package org.wellspring.crud.angular.example.service.impl;

import org.springframework.stereotype.Service;
import org.wellspring.crud.angular.example.persistence.domain.User;
import org.wellspring.crud.angular.example.persistence.repository.UserRepository;
import org.wellspring.crud.angular.example.service.UserService;
import org.wellspring.crud.service.impl.CrudServiceImpl;

@Service
public class UserServiceImpl extends
		CrudServiceImpl<UserRepository, User, Long> implements UserService {

	@Override
	public User findByEmail(String email) {
		return ((UserService) getRepository()).findByEmail(email);
	}

}
