package org.wellspring.solr.persistence.repository;

import java.io.Serializable;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.solr.repository.SolrCrudRepository;

@NoRepositoryBean
public interface SolrRepository<T, ID extends Serializable> extends
		SolrCrudRepository<T, ID> { 

}
