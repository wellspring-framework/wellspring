package org.wellspring.solr.controller.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Persistable;
import org.wellspring.solr.controller.SolrController;
import org.wellspring.solr.persistence.repository.SolrRepository;
import org.wellspring.solr.service.SolrService;

public class SolrControllerImpl<S extends SolrService<R, T, ID>, R extends SolrRepository<T, ID>, T extends Persistable<ID>, ID extends Serializable>
		implements SolrController<S, R, T, ID> {
	protected Class<T> entityClass;

	@Autowired
	private S service;

	public S getService() {
		return service;
	}

	@Override
	public void addToIndex(T entity) {
		service.addToIndex(entity);

	}

	@Override
	public void deleteFromIndex(ID id) {
		service.deleteFromIndex(id);
	}
}
