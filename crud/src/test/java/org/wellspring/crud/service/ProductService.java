package org.wellspring.crud.service;

import org.wellspring.crud.persistence.domain.Product;
import org.wellspring.crud.persistence.repository.impl.ProductRepository;

public interface ProductService extends CrudService<ProductRepository, Product, Long> {

}
