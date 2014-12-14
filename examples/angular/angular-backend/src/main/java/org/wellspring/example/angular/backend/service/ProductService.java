package org.wellspring.example.angular.backend.service;

import org.wellspring.crud.service.CrudService;
import org.wellspring.example.angular.backend.persistence.domain.Product;
import org.wellspring.example.angular.backend.persistence.repository.ProductRepository;

public interface ProductService extends CrudService<ProductRepository, Product, Long> {

}
