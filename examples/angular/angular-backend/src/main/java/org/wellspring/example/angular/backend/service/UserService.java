package org.wellspring.example.angular.backend.service;

import org.wellspring.crud.service.CrudService;
import org.wellspring.example.angular.backend.persistence.domain.User;
import org.wellspring.example.angular.backend.persistence.repository.UserRepository;

public interface UserService extends CrudService<UserRepository, User, Long> {

}
