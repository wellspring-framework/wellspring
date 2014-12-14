package org.wellspring.crud.controller;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.wellspring.crud.persistence.repository.CrudRepository;
import org.wellspring.crud.service.CrudService;

public interface RestCrudController<S extends CrudService<R, T, ID>, R extends CrudRepository<T, ID>, T, ID extends Serializable> {

	/**
	 * Returns the number of entities available.
	 * 
	 * @return the number of entities
	 */
	HttpEntity<Resource<Long>> count();

	/**
	 * Retrieves an entity by its id.
	 * 
	 * @param id
	 *            must not be {@literal null}.
	 * @return the entity with the given id or {@literal null} if none found
	 * @throws IllegalArgumentException
	 *             if {@code id} is {@literal null}
	 */

	HttpEntity<Resource<T>> delete(ID id);

	/**
	 * Deletes the given entities.
	 * 
	 * @param entities
	 * @throws IllegalArgumentException
	 *             in case the given {@link Iterable} is (@literal null}.
	 */
	HttpEntity<Resources<T>> delete(Iterable<T> entities);

	/**
	 * Deletes a given entity.
	 * 
	 * @param entity
	 * @throws IllegalArgumentException
	 *             in case the given entity is (@literal null}.
	 */
	HttpEntity<Resource<T>> delete(T entity);

	/**
	 * Deletes all entities managed by the repository.
	 */
	HttpEntity<Resources<T>> deleteAll();

	/**
	 * Deletes the entity with the given id.
	 * 
	 * @param id
	 *            must not be {@literal null}.
	 * @throws IllegalArgumentException
	 *             in case the given {@code id} is {@literal null}
	 */

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
	 * Returns all instances of the type with the given IDs.
	 * 
	 * @param ids
	 * @return
	 */
	HttpEntity<Resources<T>> findAll(Iterable<ID> ids);

	/**
	 * @param pageable
	 * @return entities with pagination
	 */
	HttpEntity<Resource<Page<T>>> query(Pageable pageable);

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
	 * Saves all given entities.
	 * 
	 * @param entities
	 * @return the saved entities
	 * @throws IllegalArgumentException
	 *             in case the given entity is (@literal null}.
	 */
	HttpEntity<Resources<T>> save(Iterable<T> entities);

	/**
	 * Saves a given entity. Use the returned instance for further operations as
	 * the save operation might have changed the entity instance completely.
	 * 
	 * @param entity
	 * @return the saved entity
	 */
	HttpEntity<Resource<T>> save(T entity, BindingResult bindingResult, Model model);

	/**
	 * Validates the entity object
	 * 
	 * @param entity
	 * @param result
	 * @param m
	 */
	HttpEntity<Resource<Boolean>> validate(T entity, BindingResult bindingResult);

	/**
	 * @param entity
	 * @param bindingResult
	 * @param model
	 * @return
	 */
	HttpEntity<Resource<T>> update(T entity, ID id, BindingResult bindingResult, Model model);

	HttpEntity<Resources<T>> update(Iterable<T> entities);

}
