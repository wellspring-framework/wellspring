package org.wellspring.crud.persistence.specification;

import static org.springframework.data.jpa.domain.Specifications.where;

import org.springframework.data.jpa.domain.Specification;
import org.wellspring.crud.persistence.domain.User;

// TODO http://www.programcreek.com/java-api-examples/index.php?api=org.springframework.data.jpa.domain.Specification
// TODO http://www.programcreek.com/java-api-examples/index.php?api=org.springframework.data.jpa.domain.Specification
public class UserSpecifications extends LikeSpecification<User, Long> {

	public Specification<User> searchTerm(String searchTerm) {
		return where(nameIsLike(searchTerm)).or(lastNameIsLike(searchTerm)).or(emailIsLike(searchTerm));
	}

	public Specification<User> nameIsLike(String searchTerm) {
		return isLike(searchTerm, "name");
	}

	public Specification<User> emailIsLike(String searchTerm) {
		return isLike(searchTerm, "email");
	}

	public Specification<User> lastNameIsLike(String searchTerm) {
		return isLike(searchTerm, "lastName");
	}
}
