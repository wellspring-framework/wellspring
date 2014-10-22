package org.wellspring.crud.angular.example.service.impl;

import org.springframework.stereotype.Service;
import org.wellspring.crud.angular.example.persistence.domain.AuthUser;
import org.wellspring.crud.angular.example.persistence.repository.AuthUserRepository;
import org.wellspring.crud.angular.example.service.AuthUserService;
import org.wellspring.crud.service.impl.RedeableServiceImpl;

@Service
public class AuthUserServiceImpl extends
		RedeableServiceImpl<AuthUserRepository, AuthUser, String> implements AuthUserService {

	@Override
	public AuthUser findByEmail(String email) {
		return ((AuthUserRepository) getRepository()).findByEmail(email);
	}

}
