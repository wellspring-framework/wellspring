package org.wellspring.solr.service.impl;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.wellspring.solr.persistence.repository.SolrRepository;
import org.wellspring.solr.service.SolrService;

public class SolrServiceImpl<R extends SolrRepository<T, ID>, T, ID extends Serializable>
		implements SolrService<R, T, ID> {
	private static final Logger LOGGER = LoggerFactory.getLogger(SolrServiceImpl.class);
	@Autowired
	private R repository;

	public R getRepository() {
		return repository;
	}

	private void assertNull(T entity) {
		Assert.notNull(entity, "entity object must not be null!");
	}

	private void assertNullId(ID id) {
		Assert.notNull(id, "ids object must not be null!");
	}

	@Override
	public void addToIndex(T entity) {
		assertNull(entity);
		repository.save(entity);

	}

	@Override
	public void deleteFromIndex(ID id) {
		assertNullId(id);
		repository.delete(id);
	}
}
