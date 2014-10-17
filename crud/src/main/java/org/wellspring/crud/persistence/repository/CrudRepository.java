package org.wellspring.crud.persistence.repository;

import java.io.Serializable;

import org.springframework.data.repository.NoRepositoryBean;
import org.wellspring.crud.persistence.domain.BasicEntity;

@NoRepositoryBean
public interface CrudRepository<T extends BasicEntity, ID extends Serializable> extends ReadableRepository<T, ID>,
		WriteableRepository<T, ID> {

}
