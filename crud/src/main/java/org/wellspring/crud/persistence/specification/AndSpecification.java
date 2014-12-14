package org.wellspring.crud.persistence.specification;

import java.io.Serializable;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class AndSpecification<T, ID extends Serializable> extends AbstractSpecification<T, ID> {

	private Specification<T, ID> first;
	private Specification<T, ID> second;

	public AndSpecification(Specification<T, ID> first, Specification<T, ID> second) {
		this.first = first;
		this.second = second;
	}

	@Override
	public boolean isSatisfiedBy(T t) {
		return first.isSatisfiedBy(t) && second.isSatisfiedBy(t);
	}

	@Override
	public Predicate toPredicate(Root<T> root, CriteriaBuilder cb) {
		return cb.and(
				first.toPredicate(root, cb),
				second.toPredicate(root, cb)
				);
	}

	@Override
	public Class<T> getType() {
		return first.getType();
	}
}