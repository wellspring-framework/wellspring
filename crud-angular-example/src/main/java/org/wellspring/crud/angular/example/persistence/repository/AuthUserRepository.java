package org.wellspring.crud.angular.example.persistence.repository;

import org.wellspring.crud.angular.example.persistence.domain.AuthUser;
import org.wellspring.crud.persistence.repository.ReadableRepository;

public interface AuthUserRepository extends ReadableRepository<AuthUser, String> {
	public AuthUser findByEmail(String email);
}
