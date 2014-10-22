package org.wellspring.crud.controller.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Persistable;
import org.springframework.data.domain.Sort;
import org.wellspring.crud.controller.ReadableController;
import org.wellspring.crud.persistence.repository.ReadableRepository;
import org.wellspring.crud.service.ReadableService;

public class ReadableControllerImpl<S extends ReadableService<R, T, ID>, R extends ReadableRepository<T, ID>, T extends Persistable<ID>, ID extends Serializable>
		implements ReadableController<S, R, T, ID> {

	@Autowired
	private ReadableService<R, T, ID> service;

	protected Class<S> serviceClass;

	@Override
	public long count() {
		return service.count();
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
}
