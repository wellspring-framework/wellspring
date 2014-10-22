package org.wellspring.crud.controller.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Persistable;
import org.springframework.data.domain.Sort;
import org.wellspring.crud.controller.CrudController;
import org.wellspring.crud.persistence.repository.CrudRepository;
import org.wellspring.crud.service.CrudService;

public class CrudControllerImpl<S extends CrudService<R, T, ID>, R extends CrudRepository<T, ID>, T extends Persistable<ID>, ID extends Serializable>
		implements CrudController<S, R, T, ID> {
	protected Class<T> entityClass;

	@Autowired
	private CrudService<R, T, ID> service;

	protected Class<S> serviceClass;

	@Override
	public long count() {
		return service.count();
	}

	@Override
	public void delete(ID id) {
		service.delete(id);
	}

	@Override
	public void delete(Iterable<? extends T> entities) {
		service.delete(entities);
	}

	@Override
	public void delete(T entity) {
		service.delete(entity);
	}

	@Override
	public void deleteAll() {
		service.deleteAll();
	}

	@Override
	public boolean exists(ID id) {
		return service.exists(id);
	}

	@Override
	public Iterable<T> findAll() {
		return service.findAll();
	}

	@Override
	public Iterable<T> findAll(Iterable<ID> ids) {
		return service.findAll(ids);
	}

	/**
	 * 
	 * @param pageable
	 * @return all paginated entities
	 */
	@Override
	public Page<T> findAll(Pageable pageable) {
		return service.findAll(pageable);
	}

	/**
	 * Returns all sorted instances of the type
	 * 
	 * @param sort
	 * @return all sorted entities
	 */
	@Override
	public Iterable<T> findAll(Sort sort) {
		return service.findAll(sort);
	}

	@Override
	public T findOne(ID id) {

		return service.findOne(id);
	}

	@Override
	public Iterable<T> save(Iterable<T> entities) {
		return service.save(entities);
	}

	@Override
	public T save(T entity) {
		return service.save(entity);
	}

	public T save2(T entity) {
		return service.save(entity);
	}

}
