package org.wellspring.solr.controller;

import java.io.Serializable;

import org.wellspring.solr.persistence.repository.SolrRepository;
import org.wellspring.solr.service.SolrService;

public interface SolrController<S extends SolrService<R, T, ID>, R extends SolrRepository<T, ID>, T, ID extends Serializable> {
	public void addToIndex(T entity);

	public void deleteFromIndex(ID id);
}
