package org.wellspring.crud.service.impl;

import org.springframework.stereotype.Service;
import org.wellspring.crud.persistence.domain.User;
import org.wellspring.crud.persistence.repository.impl.UserRepository;
import org.wellspring.crud.service.UserService;

@Service
public class UserServiceImpl extends
		CrudServiceImpl<UserRepository, User, Long> implements UserService {

}
