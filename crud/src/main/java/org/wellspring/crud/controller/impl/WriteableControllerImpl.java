package org.wellspring.crud.controller.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.data.domain.Persistable;
import org.wellspring.crud.controller.WriteableController;
import org.wellspring.crud.persistence.repository.WriteableRepository;
import org.wellspring.crud.service.WriteableService;

public class WriteableControllerImpl<S extends WriteableService<R, T, ID>, R extends WriteableRepository<T, ID>, T extends Persistable<ID>, ID extends Serializable>
		implements WriteableController<S, R, T, ID> {
	@Resource
	WriteableService<R, T, ID> service;

	public WriteableControllerImpl(WriteableService<R, T, ID> service) {
		this.service = service;
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
	public Iterable<T> save(Iterable<T> entities) {
		return service.save(entities);
	}

	@Override
	public T save(T entity) {
		return service.save(entity);
	}

}
