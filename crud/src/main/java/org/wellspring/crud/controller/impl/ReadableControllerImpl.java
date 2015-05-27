package org.wellspring.crud.controller.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wellspring.crud.controller.ReadableController;
import org.wellspring.crud.persistence.repository.ReadableRepository;
import org.wellspring.crud.service.ReadableService;
import org.wellspring.crud.util.CrudConstants;

public class ReadableControllerImpl<S extends ReadableService<R, T, ID>, R extends ReadableRepository<T, ID>, T, ID extends Serializable>
		implements
		ReadableController<S, R, T, ID> {

	@Autowired
	private S service;

	public S getService() {
		return service;
	}

	protected Class<S> serviceClass;

	@Override
	@RequestMapping(value = CrudConstants.OPERATION_COUNT, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public long count() {
		return service.count();
	}

	@Override
	@RequestMapping(value = CrudConstants.OPERATION_EXISTS, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public boolean exists(ID id) {
		return service.exists(id);
	}

	@Override
	@RequestMapping(value = CrudConstants.OPERATION_FIND_ALL, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public Iterable<T> findAll() {
		return service.findAll();
	}

	@Override
	@RequestMapping(value = CrudConstants.OPERATION_FIND_ALL_BY_IDS, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public Iterable<T> findAll(Iterable<ID> ids) {
		return service.findAll(ids);
	}

	/**
	 * @param pageable
	 * @return all paginated entities
	 */
	@Override
	@RequestMapping(value = CrudConstants.OPERATION_QUERY, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
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
	@RequestMapping(value = CrudConstants.OPERATION_FIND_ALL_SORTED, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public Iterable<T> findAll(Sort sort) {
		return service.findAll(sort);
	}

	@Override
	@RequestMapping(value = CrudConstants.OPERATION_FIND_ONE, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public T findOne(ID id) {
		return service.findOne(id);
	}

}
