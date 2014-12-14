package org.wellspring.example.angular.backend.service.impl;

import org.springframework.stereotype.Service;
import org.wellspring.crud.service.impl.CrudServiceImpl;
import org.wellspring.example.angular.backend.persistence.domain.User;
import org.wellspring.example.angular.backend.persistence.repository.UserRepository;
import org.wellspring.example.angular.backend.service.UserService;

@Service
public class UserServiceImpl extends
		CrudServiceImpl<UserRepository, User, Long> implements UserService {

}
