package org.wellspring.crud.angular.example.service;

import org.wellspring.crud.angular.example.persistence.domain.AuthUser;
import org.wellspring.crud.angular.example.persistence.repository.AuthUserRepository;
import org.wellspring.crud.service.ReadableService;

public interface AuthUserService extends ReadableService<AuthUserRepository, AuthUser, String> {

	public AuthUser findByEmail(String email);

}
