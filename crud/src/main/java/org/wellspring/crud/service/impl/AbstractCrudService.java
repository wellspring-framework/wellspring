package org.wellspring.crud.service.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.wellspring.crud.persistence.domain.BasicEntity;
import org.wellspring.crud.persistence.repository.CrudRepository;
import org.wellspring.crud.service.CrudService;

public abstract class AbstractCrudService<T extends BasicEntity, ID extends Serializable>
		implements CrudService<T, ID> {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(AbstractCrudService.class);
	@Resource
	CrudRepository<T, ID> crudRepository;

	public AbstractCrudService(org.wellspring.crud.persistence.repository.CrudRepository<T, ID> crudRepository) {
		this.crudRepository = crudRepository;
	}

	@Override
	public T findOne(ID id) {
		LOGGER.debug("Finding by pk " + id.getClass().getName() + " (" + id + ")...");
		return crudRepository.findOne(id);
	}

	@Override
	public boolean exists(ID id) {
		LOGGER.debug("Exists pk " + id.getClass().getName() + " (" + id + ")?...");
		return crudRepository.exists(id);
	}

	@Override
	public Iterable<T> findAll() {
		LOGGER.debug("Finding All ");
		return crudRepository.findAll();
	}

	@Override
	public Iterable<T> findAll(Iterable<ID> ids) {
		for (ID id : ids) {
			LOGGER.debug("Exists pk " + id.getClass().getName() + " (" + id + ")?...");
		}
		return crudRepository.findAll(ids);
	}

	@Override
	public long count() {
		return crudRepository.count();
	}

	@Override
	public Iterable<T> findAll(Sort sort) {
		return crudRepository.findAll(sort);
	}

	@Override
	public Page<T> findAll(Pageable pageable) {
		return crudRepository.findAll(pageable);
	}

	@Override
	public <S extends T> S save(S entity) {
		try {
			if (entity.getId() == null) {
				LOGGER.debug("Saving " + entity.getClass().getName() + "...");

			}
			else {
				LOGGER.debug("Saving " + entity.getClass().getName() + " ( id = " + entity.getId() + ")...");
			}
			entity = crudRepository.save(entity);
			return entity;
		} finally {
			LOGGER.debug(entity.getClass().getName() + " ( id = " + entity.getId() + ") was saved");
		}
	}

	@Override
	public <S extends T> Iterable<S> save(Iterable<S> entities) {
		for (S entity : entities) {
			if (entity.getId() == null) {
				LOGGER.debug("Saving " + entity.getClass().getName() + "...");

			}
			else {
				LOGGER.debug("Saving " + entity.getClass().getName() + " ( id = " + entity.getId() + ")...");
			}
		}
		return crudRepository.save(entities);
	}

	@Override
	public void delete(ID id) {
		crudRepository.delete(id);
	}

	@Override
	public void delete(T entity) {
		crudRepository.delete(entity);
	}

	@Override
	public void delete(Iterable<? extends T> entities) {
		crudRepository.delete(entities);
	}

	@Override
	public void deleteAll() {
		crudRepository.deleteAll();
	}
}
