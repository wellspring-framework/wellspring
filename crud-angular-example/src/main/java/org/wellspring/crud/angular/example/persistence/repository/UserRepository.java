package org.wellspring.crud.angular.example.persistence.repository;

import org.wellspring.crud.angular.example.persistence.domain.User;
import org.wellspring.crud.persistence.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}
