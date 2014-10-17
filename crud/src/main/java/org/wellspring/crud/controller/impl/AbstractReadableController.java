package org.wellspring.crud.controller.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.wellspring.crud.controller.ReadableController;
import org.wellspring.crud.persistence.domain.BasicEntity;
import org.wellspring.crud.service.CrudService;

public class AbstractReadableController<T extends BasicEntity, ID extends Serializable> implements ReadableController<T, ID> {
	@Resource
	CrudService<T, ID> crudService;

	public AbstractReadableController(CrudService<T, ID> crudService) {
		this.crudService = crudService;
	}

	@Override
	public T findOne(ID id) {

		return crudService.findOne(id);
	}

	@Override
	public boolean exists(ID id) {
		return crudService.exists(id);
	}

	@Override
	public Iterable<T> findAll() {
		return crudService.findAll();
	}

	@Override
	public Iterable<T> findAll(Iterable<ID> ids) {
		return crudService.findAll(ids);
	}

	@Override
	public long count() {
		return crudService.count();
	}

	/**
	 * Returns all sorted instances of the type
	 * 
	 * @param sort
	 * @return all sorted entities
	 */
	@Override
	public Iterable<T> findAll(Sort sort) {
		return crudService.findAll(sort);
	}

	/**
	 * 
	 * @param pageable
	 * @return all paginated entities
	 */
	@Override
	public Page<T> findAll(Pageable pageable) {
		return crudService.findAll(pageable);
	}
}
