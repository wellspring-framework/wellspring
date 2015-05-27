package org.wellspring.crud.controller.impl;

import java.io.Serializable;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Persistable;
import org.springframework.data.domain.Sort;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wellspring.crud.controller.CrudController;
import org.wellspring.crud.exceptions.InvalidRequestException;
import org.wellspring.crud.persistence.repository.CrudRepository;
import org.wellspring.crud.service.CrudService;
import org.wellspring.crud.util.CrudConstants;

public class CrudControllerImpl<S extends CrudService<R, T, ID>, R extends CrudRepository<T, ID>, T extends Persistable<ID>, ID extends Serializable>
		implements CrudController<S, R, T, ID> {

	@Autowired
	private S service;

	public S getService() {
		return service;
	}

	@Override
	@RequestMapping(value = CrudConstants.OPERATION_COUNT, method = RequestMethod.POST)
	@ResponseBody
	public long count() {
		return service.count();
	}

	@Override
	@RequestMapping(value = CrudConstants.OPERATION_DELETE_BY_ID + CrudConstants.ID, method = RequestMethod.GET)
	public void delete(@PathVariable("id") ID id) {
		service.delete(id);
	}

	@Override
	@RequestMapping(value = CrudConstants.OPERATION_DELETE_BY_ENTITIES, method = RequestMethod.POST)
	public void delete(Iterable<? extends T> entities) {
		service.delete(entities);
	}

	@Override
	@RequestMapping(value = CrudConstants.OPERATION_DELETE_BY_ENTITY, method = RequestMethod.POST)
	@ResponseBody
	public void delete(@RequestBody T entity) {
		service.delete(entity);

	}

	@Override
	@RequestMapping(value = CrudConstants.OPERATION_DELETE_ALL, method = RequestMethod.POST)
	@ResponseBody
	public void deleteAll() {
		service.deleteAll();
	}

	@Override
	@RequestMapping(value = CrudConstants.OPERATION_EXISTS + CrudConstants.ID, method = RequestMethod.GET)
	@ResponseBody
	public boolean exists(@PathVariable("id") ID id) {
		return service.exists(id);
	}

	@Override
	@RequestMapping(value = CrudConstants.OPERATION_FIND_ALL, method = RequestMethod.POST)
	@ResponseBody
	public Iterable<T> findAll() {
		return service.findAll();
	}

	@Override
	@RequestMapping(value = CrudConstants.OPERATION_FIND_ALL_BY_IDS, method = RequestMethod.POST)
	@ResponseBody
	public Iterable<T> findAll(Iterable<ID> ids) {
		return service.findAll(ids);
	}

	/**
	 * @param pageable
	 * @return all paginated entities
	 */
	@Override
	@RequestMapping(value = CrudConstants.OPERATION_QUERY, method = RequestMethod.POST)
	@ResponseBody
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
	@RequestMapping(value = CrudConstants.OPERATION_FIND_ALL_SORTED, method = RequestMethod.POST)
	@ResponseBody
	public Iterable<T> findAll(Sort sort) {
		return service.findAll(sort);
	}

	@Override
	@RequestMapping(value = CrudConstants.OPERATION_FIND_ONE + CrudConstants.ID, method = RequestMethod.GET)
	@ResponseBody
	public T findOne(@PathVariable("id") ID id) {
		return service.findOne(id);
	}

	@Override
	@RequestMapping(value = CrudConstants.OPERATION_SAVE_ENTITIES, method = RequestMethod.POST)
	@ResponseBody
	public Iterable<T> save(Iterable<T> entities) {
		return service.save(entities);
	}

	@Override
	@RequestMapping(value = CrudConstants.OPERATION_SAVE, method = RequestMethod.POST)
	@ResponseBody
	public T save(@RequestBody T entity) {
		return service.save(entity);
	}

	@Override
	@RequestMapping(value = CrudConstants.OPERATION_VALIDATE, method = RequestMethod.POST)
	public boolean validate(@RequestBody @Valid T entity, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new InvalidRequestException("Invalid Entity ", bindingResult);
		}
		return true;
	}

}
