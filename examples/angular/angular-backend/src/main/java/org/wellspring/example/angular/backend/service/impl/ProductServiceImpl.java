package org.wellspring.example.angular.backend.service.impl;

import org.springframework.stereotype.Service;
import org.wellspring.crud.service.impl.CrudServiceImpl;
import org.wellspring.example.angular.backend.persistence.domain.Product;
import org.wellspring.example.angular.backend.persistence.repository.ProductRepository;
import org.wellspring.example.angular.backend.service.ProductService;

@Service
public class ProductServiceImpl extends
		CrudServiceImpl<ProductRepository, Product, Long> implements ProductService {

}
