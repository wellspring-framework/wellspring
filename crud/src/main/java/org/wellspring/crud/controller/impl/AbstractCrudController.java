package org.wellspring.crud.controller.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.wellspring.crud.controller.CrudController;
import org.wellspring.crud.persistence.domain.BasicEntity;
import org.wellspring.crud.service.CrudService;

public class AbstractCrudController<T extends BasicEntity, ID extends Serializable> implements CrudController<T, ID> {
	@Resource
	CrudService<T, ID> crudService;

	public AbstractCrudController(CrudService<T, ID> crudService) {
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

	@Override
	public <S extends T> S save(S entity) {
		return crudService.save(entity);
	}

	@Override
	public <S extends T> Iterable<S> save(Iterable<S> entities) {
		return crudService.save(entities);
	}

	@Override
	public void delete(ID id) {
		crudService.delete(id);
	}

	@Override
	public void delete(T entity) {
		crudService.delete(entity);
	}

	@Override
	public void delete(Iterable<? extends T> entities) {
		crudService.delete(entities);
	}

	@Override
	public void deleteAll() {
		crudService.deleteAll();
	}

}
