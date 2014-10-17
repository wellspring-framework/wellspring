package org.wellspring.crud.controller;

import java.io.Serializable;

import org.wellspring.crud.persistence.domain.BasicEntity;

public interface CrudController<T extends BasicEntity, ID extends Serializable> extends ReadableController<T, ID>, WriteableController<T, ID> {

}
