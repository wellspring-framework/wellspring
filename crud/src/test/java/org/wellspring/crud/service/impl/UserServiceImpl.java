package org.wellspring.crud.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wellspring.crud.persistence.domain.User;
import org.wellspring.crud.persistence.repository.UserRepository;
import org.wellspring.crud.service.UserService;

@Service
public class UserServiceImpl extends AbstractCrudService<User, Long> implements UserService {

	@Autowired
	public UserServiceImpl(UserRepository crudRepository) {
		super(crudRepository);
	}

}
