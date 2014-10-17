package org.wellspring.crud.controller.impl;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wellspring.crud.persistence.domain.BasicEntity;
import org.wellspring.crud.service.CrudService;

public class AbstractRestCrudController<T extends BasicEntity, ID extends Serializable>
		extends AbstractCrudController<T, ID> {
	public AbstractRestCrudController(CrudService<T, ID> service) {
		super(service);
	}

	@Override
	@RequestMapping(value = "/save", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public <S extends T> S save(S entity) {
		return super.save(entity);
	}

	@Override
	@RequestMapping(value = "/saveEntities", method = RequestMethod.GET, headers = "Accept=application/json")
	public <S extends T> Iterable<S> save(Iterable<S> entities) {
		return super.save(entities);
	}

	@Override
	@RequestMapping(value = "/findOne", method = RequestMethod.GET, headers = "Accept=application/json")
	public T findOne(ID id) {
		return super.findOne(id);
	}

	@Override
	@RequestMapping(value = "/exists", method = RequestMethod.GET, headers = "Accept=application/json")
	public boolean exists(ID id) {
		return super.exists(id);
	}

	@Override
	@RequestMapping(value = "/findAll", method = RequestMethod.GET, headers = "Accept=application/json")
	public Iterable<T> findAll() {
		return super.findAll();
	}

	@Override
	@RequestMapping(value = "/findAllByIds", method = RequestMethod.GET, headers = "Accept=application/json")
	public Iterable<T> findAll(Iterable<ID> ids) {
		return super.findAll(ids);
	}

	@Override
	@RequestMapping(value = "/count", method = RequestMethod.GET, headers = "Accept=application/json")
	public long count() {
		return super.count();
	}

	@Override
	@RequestMapping(value = "/deleteById", method = RequestMethod.GET, headers = "Accept=application/json")
	public void delete(ID id) {
		super.delete(id);
	}

	/**
	 * Returns all sorted instances of the type
	 * 
	 * @param sort
	 * @return all sorted entities
	 */
	@Override
	@RequestMapping(value = "/findAllSorted", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public Iterable<T> findAll(Sort sort) {
		return super.findAll(sort);
	}

	/**
	 * 
	 * @param pageable
	 * @return all paginated entities
	 */
	@Override
	@RequestMapping(value = "/findAllPaginated", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public Page<T> findAll(Pageable pageable) {
		return super.findAll(pageable);
	}

	@Override
	@RequestMapping(value = "/deleteByEntity", method = RequestMethod.GET, headers = "Accept=application/json")
	public void delete(T entity) {
		super.delete(entity);

	}

	@Override
	@RequestMapping(value = "/deleteByEntities", method = RequestMethod.GET, headers = "Accept=application/json")
	public void delete(Iterable<? extends T> entities) {
		super.delete(entities);
	}

	@Override
	@RequestMapping(value = "/deleteAll", method = RequestMethod.GET, headers = "Accept=application/json")
	public void deleteAll() {
		super.deleteAll();
	}
}
