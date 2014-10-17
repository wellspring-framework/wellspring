package org.wellspring.crud.persistence.repository;

import org.springframework.stereotype.Repository;
import org.wellspring.crud.persistence.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}
