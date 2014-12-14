package org.wellspring.crud.service.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Persistable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.Assert;
import org.wellspring.crud.persistence.repository.CrudRepository;
import org.wellspring.crud.service.CrudService;

// TODO http://stackoverflow.com/questions/27950246/spring-data-overriding-default-methods-for-some-repositories
public class CrudServiceImpl<R extends CrudRepository<T, ID>, T, ID extends Serializable>
		implements CrudService<R, T, ID> {
	private static final Logger LOGGER = LoggerFactory.getLogger(CrudServiceImpl.class);

	@Resource
	EntityManager entityManager;

	// @Resource
	// private CrudRepository<T, ID> repository;
	@Autowired
	private R repository;

	private Class<T> entityClass;

	@SuppressWarnings("unchecked")
	public CrudServiceImpl() {
		this.entityClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[1];
	}

	public R getRepository() {
		return repository;
	}

	@Override
	public long count() {
		return repository.count();
	}

	@Override
	public void delete(ID id) {
		assertNullId(id);
		repository.delete(id);
	}

	@Override
	public void delete(Iterable<? extends T> entities) {
		assertNullEntities(entities);
		repository.delete(entities);
	}

	@Override
	public void delete(T entity) {
		assertNull(entity);
		repository.delete(entity);
	}

	@Override
	public void deleteAll() {
		repository.deleteAll();
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
		LOGGER.debug("Finding All Entities of type " + entityClass.getSimpleName());
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
	public <S extends T> Iterable<S> save(Iterable<S> entities) {
		assertNullEntities(entities);
		for (S entity : entities) {
			if (entity instanceof Persistable<?>) {
				if (((Persistable<?>) entity).getId() == null) {
					LOGGER.debug("Saving " + entity.getClass().getName() + "...");

				} else {
					LOGGER.debug("Saving " + entity.getClass().getName()
							+ " ( id = " + ((Persistable<?>) entity).getId() + ")...");
				}
			}
		}
		return repository.save(entities);
	}

	@Override
	public <S extends T> S save(S entity) {
		try {
			assertNull(entity);
			if (entity instanceof Persistable<?>) {
				if (((Persistable<?>) entity).getId() == null) {
					LOGGER.debug("Saving " + entity.getClass().getName() + "...");

				} else {
					LOGGER.debug("Saving " + entity.getClass().getName()
							+ " ( id = " + ((Persistable<?>) entity).getId() + ")...");
				}
			}
			entity = repository.save(entity);
			return entity;
		} finally {
			if (entity instanceof Persistable<?>) {
				LOGGER.debug(entity.getClass().getName() + " ( id = "
						+ ((Persistable<?>) entity).getId() + ") was saved");
			}
		}
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

	private void validateEntity(ID id) {
		if (repository.findOne(id) == null) {
			throw new org.wellspring.crud.exceptions.EntityNotFoundException(String.valueOf(id));
		}
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
