package org.wellspring.crud.persistence.specification;

import java.io.Serializable;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public interface Specification<T, ID extends Serializable> {
	boolean isSatisfiedBy(T t);

	Predicate toPredicate(Root<T> root, CriteriaBuilder cb);

	Class<T> getType();
}