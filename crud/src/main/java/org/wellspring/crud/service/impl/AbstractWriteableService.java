package org.wellspring.crud.service.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.CrudRepository;
import org.wellspring.crud.persistence.domain.BasicEntity;
import org.wellspring.crud.service.WriteableService;

public abstract class AbstractWriteableService<T extends BasicEntity, ID extends Serializable>
		implements WriteableService<T, ID> {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(AbstractWriteableService.class);
	@Resource
	CrudRepository<T, ID> repository;

	public AbstractWriteableService(CrudRepository<T, ID> repository) {
		this.repository = repository;
	}

	@Override
	public <S extends T> S save(S entity) {
		try {
			if (entity.getId() == null) {
				LOGGER.debug("Saving " + entity.getClass().getName() + "...");

			} else {
				LOGGER.debug("Saving " + entity.getClass().getName() + " ( id = " + entity.getId() + ")...");
			}
			entity = repository.save(entity);
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

			} else {
				LOGGER.debug("Saving " + entity.getClass().getName() + " ( id = " + entity.getId() + ")...");
			}
		}
		return repository.save(entities);
	}

	@Override
	public void delete(ID id) {
		repository.delete(id);

	}

	@Override
	public void delete(T entity) {
		repository.delete(entity);

	}

	@Override
	public void delete(Iterable<? extends T> entities) {
		repository.delete(entities);

	}

	@Override
	public void deleteAll() {
		repository.deleteAll();
	}
}
