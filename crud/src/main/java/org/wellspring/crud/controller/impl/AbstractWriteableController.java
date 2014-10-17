package org.wellspring.crud.controller.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.wellspring.crud.controller.WriteableController;
import org.wellspring.crud.persistence.domain.BasicEntity;
import org.wellspring.crud.service.CrudService;

public class AbstractWriteableController<T extends BasicEntity, ID extends Serializable> implements WriteableController<T, ID> {
	@Resource
	CrudService<T, ID> crudService;

	public AbstractWriteableController(CrudService<T, ID> crudService) {
		this.crudService = crudService;
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
