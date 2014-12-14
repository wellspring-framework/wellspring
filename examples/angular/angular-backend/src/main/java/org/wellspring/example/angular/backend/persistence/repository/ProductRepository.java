package org.wellspring.example.angular.backend.persistence.repository;

import org.wellspring.crud.persistence.repository.CrudRepository;
import org.wellspring.example.angular.backend.persistence.domain.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

}