package org.wellspring.crud.service.impl;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.Assert;
import org.wellspring.crud.persistence.repository.ReadableRepository;
import org.wellspring.crud.service.ReadableService;

public class RedeableServiceImpl<R extends ReadableRepository<T, ID>, T, ID extends Serializable>
		implements ReadableService<R, T, ID> {
	private static final Logger LOGGER = LoggerFactory.getLogger(RedeableServiceImpl.class);

	@Autowired
	private R repository;

	public R getRepository() {
		return repository;
	}

	@Override
	public long count() {
		return repository.count();
	}

	@Override
	public boolean exists(ID id) {
		assertNullId(id);
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
		assertNullIds(ids);
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
		assertNullId(id);
		LOGGER.debug("Finding by pk " + id.getClass().getName() + " (" + id
				+ ")...");
		return repository.findOne(id);
	}

	@Override
	public T findOne(Specification<T> spec) {
		return repository.findOne(spec);
	}

	@Override
	public List<T> findAll(Specification<T> spec) {
		return repository.findAll(spec);
	}

	@Override
	public Page<T> findAll(Specification<T> spec, Pageable pageable) {
		return repository.findAll(spec, pageable);
	}

	@Override
	public List<T> findAll(Specification<T> spec, Sort sort) {
		return repository.findAll(spec, sort);
	}

	@Override
	public long count(Specification<T> spec) {
		return repository.count(spec);
	}

	private void assertNull(T entity) {
		Assert.notNull(entity, "entity object must not be null!");
	}

	private void assertNullId(ID id) {
		Assert.notNull(id, "ids object must not be null!");
	}

	private void assertNullIds(Iterable<ID> ids) {
		Assert.notNull(ids, "ids object must not be null!");
	}

	private void assertNullEntities(Iterable<? extends T> entities) {
		Assert.notNull(entities, "entities object must not be null!");
	}

}
