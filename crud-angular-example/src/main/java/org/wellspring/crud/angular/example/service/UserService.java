package org.wellspring.crud.angular.example.service;

import org.wellspring.crud.angular.example.persistence.domain.User;
import org.wellspring.crud.angular.example.persistence.repository.UserRepository;
import org.wellspring.crud.service.CrudService;

public interface UserService extends CrudService<UserRepository, User, Long> {

	public User findByEmail(String email);

}
