package org.wellspring.solr.persistence.listener;

import java.io.Serializable;

import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.wellspring.solr.persistence.repository.SolrRepository;
import org.wellspring.solr.service.SolrService;

public abstract class SolrListener<S extends SolrService<R, T, ID>, R extends SolrRepository<T, ID>, T, ID extends Serializable> {
	public static final Logger LOGGER = LoggerFactory.getLogger(SolrListener.class);

	@Autowired
	private S service;

	private void assertNull(T entity) {
		Assert.notNull(entity, "entity object must not be null!");
	}

	private void assertNullId(ID id) {
		Assert.notNull(id, "ids object must not be null!");
	}

	/**
	 * Triggered after a JPA entity is persisted or updated
	 * 
	 * @param entity
	 *            The entity to index
	 */
	@PostPersist
	@PostUpdate
	public void postPersist(T entity) {
		assertNull(entity);
		service.addToIndex(entity);
	}

	/**
	 * Triggered after a JPA entity is deleted
	 * 
	 * @param entity
	 *            The entity to remove from the index
	 */
	@PostRemove
	public void postRemove(ID id) {
		assertNullId(id);
		service.deleteFromIndex(id);
	}
}
