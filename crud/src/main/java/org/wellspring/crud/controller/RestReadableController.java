package org.wellspring.crud.controller;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.validation.BindingResult;
import org.wellspring.crud.persistence.repository.ReadableRepository;
import org.wellspring.crud.service.ReadableService;

public interface RestReadableController<S extends ReadableService<R, T, ID>, R extends ReadableRepository<T, ID>, T, ID extends Serializable> {

	/**
	 * Returns the number of entities available.
	 * 
	 * @return the number of entities
	 */
	HttpEntity<Resource<Long>> count();

	/**
	 * Returns whether an entity with the given id exists.
	 * 
	 * @param id
	 *            must not be {@literal null}.
	 * @return true if an entity with the given id exists, {@literal false} otherwise
	 * @throws IllegalArgumentException
	 *             if {@code id} is {@literal null}
	 */
	HttpEntity<Resource<Boolean>> exists(ID id);

	/**
	 * @param pageable
	 * @return entities with pagination
	 */
	HttpEntity<Resource<Page<T>>> query(Pageable pageable);

	/**
	 * Returns all instances of the type with the given IDs.
	 * 
	 * @param ids
	 * @return
	 */
	HttpEntity<Resources<T>> findAll(Iterable<ID> ids);

	/**
	 * Returns all sorted instances of the type
	 * 
	 * @param sort
	 * @return all sorted entities
	 */
	HttpEntity<Resources<T>> findAll(Sort sort);

	/**
	 * Retrieves an entity by its id.
	 * 
	 * @param id
	 *            must not be {@literal null}.
	 * @return the entity with the given id or {@literal null} if none found
	 * @throws IllegalArgumentException
	 *             if {@code id} is {@literal null}
	 */
	HttpEntity<Resource<T>> findOne(ID id);

	/**
	 * Validates the entity object
	 * 
	 * @param entity
	 * @param result
	 * @param m
	 */
	HttpEntity<Resource<Boolean>> validate(T entity, BindingResult bindingResult);

}
