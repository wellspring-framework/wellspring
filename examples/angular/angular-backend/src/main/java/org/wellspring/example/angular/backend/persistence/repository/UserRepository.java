package org.wellspring.example.angular.backend.persistence.repository;

import org.wellspring.crud.persistence.repository.CrudRepository;
import org.wellspring.example.angular.backend.persistence.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {

}