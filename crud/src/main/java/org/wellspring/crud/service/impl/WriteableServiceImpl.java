package org.wellspring.crud.service.impl;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.wellspring.crud.persistence.repository.WriteableRepository;
import org.wellspring.crud.service.WriteableService;

public class WriteableServiceImpl<R extends WriteableRepository<T, ID>, T extends Persistable<ID>, ID extends Serializable>
		implements WriteableService<R, T, ID> {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(RedeableServiceImpl.class);
	@Autowired
	private JpaRepository<T, ID> repository;

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
