package org.wellspring.crud.service.impl;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Persistable;
import org.springframework.data.domain.Sort;
import org.wellspring.crud.persistence.repository.ReadableRepository;
import org.wellspring.crud.service.ReadableService;

public class RedeableServiceImpl<R extends ReadableRepository<T, ID>, T extends Persistable<ID>, ID extends Serializable>
		implements ReadableService<R, T, ID> {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(RedeableServiceImpl.class);

	@Autowired
	private ReadableRepository<T, ID> repository;

	public ReadableRepository<T, ID> getRepository() {
		return repository;
	}

	@Override
	public long count() {
		return repository.count();
	}

	@Override
	public boolean exists(ID id) {
		LOGGER.debug("Exists pk " + id.getClass().getName() + " (" + id
				+ ")?...");
		return repository.exists(id);
	}

	@Override
	public Iterable<T> findAll() {
		LOGGER.debug("Finding All ");
		return repository.findAll();
	}

	@Override
	public Iterable<T> findAll(Iterable<ID> ids) {
		for (ID id : ids) {
			LOGGER.debug("Exists pk " + id.getClass().getName() + " (" + id
					+ ")?...");
		}
		return repository.findAll(ids);
	}

	@Override
	public Page<T> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Override
	public Iterable<T> findAll(Sort sort) {
		return repository.findAll(sort);
	}

	@Override
	public T findOne(ID id) {
		LOGGER.debug("Finding by pk " + id.getClass().getName() + " (" + id
				+ ")...");
		return repository.findOne(id);
	}

}
