package org.wellspring.crud.service.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.wellspring.crud.persistence.domain.BasicEntity;
import org.wellspring.crud.persistence.repository.ReadableRepository;
import org.wellspring.crud.service.ReadableService;

public abstract class AbstractRedeableService<T extends BasicEntity, ID extends Serializable>
		implements ReadableService<T, ID> {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(AbstractRedeableService.class);

	@Resource
	ReadableRepository<T, ID> repository;

	public AbstractRedeableService(ReadableRepository<T, ID> repository) {
		this.repository = repository;
	}

	@Override
	public T findOne(ID id) {
		LOGGER.debug("Finding by pk " + id.getClass().getName() + " (" + id + ")...");
		return repository.findOne(id);
	}

	@Override
	public boolean exists(ID id) {
		LOGGER.debug("Exists pk " + id.getClass().getName() + " (" + id + ")?...");
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
			LOGGER.debug("Exists pk " + id.getClass().getName() + " (" + id + ")?...");
		}
		return repository.findAll(ids);
	}

	@Override
	public long count() {
		return repository.count();
	}

	@Override
	public Iterable<T> findAll(Sort sort) {
		return repository.findAll(sort);
	}

	@Override
	public Page<T> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

}
