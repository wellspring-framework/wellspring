package org.wellspring.crud.persistence.specification;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wellspring.crud.exceptions.NotImplementedException;

abstract public class AbstractSpecification<T, ID extends Serializable> implements Specification<T, ID> {

	public static final Logger LOGGER = LoggerFactory.getLogger(AbstractSpecification.class);

	@Override
	public boolean isSatisfiedBy(T t) {
		try {
			throw new NotImplementedException();
		} catch (NotImplementedException e) {
			LOGGER.error("not implemented", e);
		}
		return false;
	}

	@Override
	public Predicate toPredicate(Root<T> poll, CriteriaBuilder cb) {
		try {
			throw new NotImplementedException();
		} catch (NotImplementedException e) {
			LOGGER.error("not implemented", e);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public <T, R> Path<R> getPath(Class<R> resultType, Path<T> root, String path) {
		String[] pathElements = path.split("\\.");
		Path<?> retVal = root;
		for (String pathEl : pathElements) {
			retVal = retVal.get(pathEl);
		}
		return (Path<R>) retVal;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Class<T> getType() {
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		return (Class<T>) type.getActualTypeArguments()[0];
	}
}