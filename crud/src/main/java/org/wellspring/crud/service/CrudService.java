package org.wellspring.crud.service;

import java.io.Serializable;

import org.wellspring.crud.persistence.domain.BasicEntity;

public interface CrudService<T extends BasicEntity, ID extends Serializable>
		extends ReadableService<T, ID>, WriteableService<T, ID> {

}
