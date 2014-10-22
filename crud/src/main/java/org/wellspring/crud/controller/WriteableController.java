package org.wellspring.crud.controller;

import java.io.Serializable;

import org.springframework.data.domain.Persistable;
import org.wellspring.crud.persistence.repository.WriteableRepository;
import org.wellspring.crud.service.WriteableService;

public interface WriteableController<S extends WriteableService<R, T, ID>, R extends WriteableRepository<T, ID>, T extends Persistable<ID>, ID extends Serializable> {
	/**
	 * Retrieves an entity by its id.
	 * 
	 * @param id
	 *            must not be {@literal null}.
	 * @return the entity with the given id or {@literal null} if none found
	 * @throws IllegalArgumentException
	 *             if {@code id} is {@literal null}
	 */

	void delete(ID id);

	/**
	 * Deletes the given entities.
	 * 
	 * @param entities
	 * @throws IllegalArgumentException
	 *             in case the given {@link Iterable} is (@literal null}.
	 */
	void delete(Iterable<? extends T> entities);

	/**
	 * Deletes a given entity.
	 * 
	 * @param entity
	 * @throws IllegalArgumentException
	 *             in case the given entity is (@literal null}.
	 */
	void delete(T entity);

	/**
	 * Deletes all entities managed by the repository.
	 */
	void deleteAll();

	/**
	 * Saves all given entities.
	 * 
	 * @param entities
	 * @return the saved entities
	 * @throws IllegalArgumentException
	 *             in case the given entity is (@literal null}.
	 */
	Iterable<T> save(Iterable<T> entities);

	/**
	 * Saves a given entity. Use the returned instance for further operations as
	 * the save operation might have changed the entity instance completely.
	 * 
	 * @param entity
	 * @return the saved entity
	 */
	T save(T entity);
}
