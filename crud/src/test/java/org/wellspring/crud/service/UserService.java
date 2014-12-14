package org.wellspring.crud.service;

import java.util.List;

import org.wellspring.crud.persistence.domain.User;
import org.wellspring.crud.persistence.repository.impl.UserRepository;

public interface UserService extends CrudService<UserRepository, User, Long> {

	List<User> findBySearchTerm(String searchTerm);

}
