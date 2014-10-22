package org.wellspring.crud.controller.impl.rest;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Persistable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wellspring.crud.controller.CrudController;
import org.wellspring.crud.controller.impl.CrudControllerImpl;
import org.wellspring.crud.persistence.repository.CrudRepository;
import org.wellspring.crud.service.CrudService;
import org.wellspring.crud.util.CrudConstants;

public class RestCrudControllerImpl<S extends CrudService<R, T, ID>, R extends CrudRepository<T, ID>, T extends Persistable<ID>, ID extends Serializable>
		extends CrudControllerImpl<S, R, T, ID> implements
		CrudController<S, R, T, ID> {

	@Override
	@RequestMapping(value = CrudConstants.OPERATION_COUNT, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public long count() {
		return super.count();
	}

	@Override
	@RequestMapping(value = CrudConstants.OPERATION_DELETE_BY_ID + CrudConstants.ID, method = RequestMethod.GET, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public void delete(@PathVariable("id") ID id) {
		super.delete(id);
	}

	@Override
	@RequestMapping(value = CrudConstants.OPERATION_DELETE_BY_ENTITIES, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public void delete(Iterable<? extends T> entities) {
		super.delete(entities);
	}

	@Override
	@RequestMapping(value = CrudConstants.OPERATION_DELETE_BY_ENTITY, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public void delete(@RequestBody T entity) {
		super.delete(entity);

	}

	@Override
	@RequestMapping(value = CrudConstants.OPERATION_DELETE_ALL, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public void deleteAll() {
		super.deleteAll();
	}

	@Override
	@RequestMapping(value = CrudConstants.OPERATION_EXISTS + CrudConstants.ID, method = RequestMethod.GET, consumes = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public boolean exists(@PathVariable("id") ID id) {
		return super.exists(id);
	}

	@Override
	@RequestMapping(value = CrudConstants.OPERATION_FIND_ALL, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public Iterable<T> findAll() {
		return super.findAll();
	}

	@Override
	@RequestMapping(value = CrudConstants.OPERATION_FIND_ALL_BY_IDS, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public Iterable<T> findAll(Iterable<ID> ids) {
		return super.findAll(ids);
	}

	/**
	 * 
	 * @param pageable
	 * @return all paginated entities
	 */
	@Override
	@RequestMapping(value = CrudConstants.OPERATION_FIND_ALL_PAGINATED, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public Page<T> findAll(Pageable pageable) {
		return super.findAll(pageable);
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
		return super.findAll(sort);
	}

	@Override
	@RequestMapping(value = CrudConstants.OPERATION_FIND_ONE + CrudConstants.ID, method = RequestMethod.GET, consumes = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public T findOne(@PathVariable("id") ID id) {
		return super.findOne(id);
	}

	@Override
	@RequestMapping(value = CrudConstants.OPERATION_SAVE_ENTITIES, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public Iterable<T> save(Iterable<T> entities) {
		return super.save(entities);
	}

	@Override
	@RequestMapping(value = CrudConstants.OPERATION_SAVE, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public T save(@RequestBody T entity) {
		return super.save(entity);
	}
}
