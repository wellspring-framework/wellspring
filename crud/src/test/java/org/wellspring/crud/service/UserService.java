package org.wellspring.crud.service;

import org.wellspring.crud.persistence.domain.User;
import org.wellspring.crud.persistence.repository.impl.UserRepository;

public interface UserService extends CrudService<UserRepository, User, Long> {

}
