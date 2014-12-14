package org.wellspring.crud.persistence.specification;

import java.io.Serializable;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

public class LikeSpecification<T, ID extends Serializable> extends AbstractSpecification<T, ID> {

	@Override
	public boolean isSatisfiedBy(T t) {
		return t != null;
	}

	public Specification<T> isLike(final String searchTerm, final String field) {
		return new Specification<T>() {
			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				String likePattern = getLikePattern(searchTerm);
				return cb.like(cb.lower(root.<String> get(field)), likePattern);
			}
		};
	}

	public Specification<T> isLikeWithOrderBy(final String searchTerm, final String field, final List<Order> orderBy) {
		return new Specification<T>() {
			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				if (!orderBy.isEmpty()) {
					query.orderBy(orderBy);
				}

				String likePattern = getLikePattern(searchTerm);
				return cb.like(cb.lower(root.<String> get(field)), likePattern);
			}
		};
	}

	public Specification<T> isLikeFromRelation(final String searchTerm, final String field) {
		return new Specification<T>() {
			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				String likePattern = getLikePattern(searchTerm);
				return cb.like(cb.lower(getPath(String.class, root, field)), likePattern);
			}
		};
	}

	private static String getLikePattern(final String searchTerm) {
		StringBuilder pattern = new StringBuilder();
		pattern.append("%");
		pattern.append(searchTerm.toLowerCase());
		pattern.append("%");
		return pattern.toString();
	}

}
