package org.wellspring.crud.service.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Persistable;
import org.springframework.data.domain.Sort;
import org.wellspring.crud.persistence.repository.CrudRepository;
import org.wellspring.crud.service.CrudService;

public class CrudServiceImpl<R extends CrudRepository<T, ID>, T extends Persistable<ID>, ID extends Serializable>
		implements CrudService<R, T, ID> {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(RedeableServiceImpl.class);

	@Resource
	private CrudRepository<T, ID> repository;

	public CrudRepository<T, ID> getRepository() {
		return repository;
	}

	@Override
	public long count() {
		return repository.count();
	}

	@Override
	public void delete(ID id) {
		repository.delete(id);
	}

	@Override
	public void delete(Iterable<? extends T> entities) {
		repository.delete(entities);
	}

	@Override
	public void delete(T entity) {
		repository.delete(entity);
	}

	@Override
	public void deleteAll() {
		repository.deleteAll();
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

	@Override
	public <S extends T> Iterable<S> save(Iterable<S> entities) {
		for (S entity : entities) {
			if (entity.getId() == null) {
				LOGGER.debug("Saving " + entity.getClass().getName() + "...");

			} else {
				LOGGER.debug("Saving " + entity.getClass().getName()
						+ " ( id = " + entity.getId() + ")...");
			}
		}
		return repository.save(entities);
	}

	@Override
	public <S extends T> S save(S entity) {
		try {
			if (entity.getId() == null) {
				LOGGER.debug("Saving " + entity.getClass().getName() + "...");

			} else {
				LOGGER.debug("Saving " + entity.getClass().getName()
						+ " ( id = " + entity.getId() + ")...");
			}
			entity = repository.save(entity);
			return entity;
		} finally {
			LOGGER.debug(entity.getClass().getName() + " ( id = "
					+ entity.getId() + ") was saved");
		}
	}
}
