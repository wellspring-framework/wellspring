package org.wellspring.solr.service;

import java.io.Serializable;

import org.wellspring.solr.persistence.repository.SolrRepository;

public interface SolrService<R extends SolrRepository<T, ID>, T, ID extends Serializable> {

	public void addToIndex(T todoEntry);

	public void deleteFromIndex(ID id);
}
