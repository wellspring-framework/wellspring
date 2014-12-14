package org.wellspring.crud.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.wellspring.crud.persistence.domain.User;
import org.wellspring.crud.persistence.repository.impl.UserRepository;
import org.wellspring.crud.persistence.specification.UserSpecifications;
import org.wellspring.crud.service.UserService;

@Service
public class UserServiceImpl extends
		CrudServiceImpl<UserRepository, User, Long> implements UserService {

	@Override
	public List<User> findBySearchTerm(String searchTerm) {
		return getRepository().findAll(new UserSpecifications().searchTerm(searchTerm));
	}

}
