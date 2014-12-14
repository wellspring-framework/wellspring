package org.wellspring.example.angular.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wellspring.crud.controller.impl.RestCrudControllerImpl;
import org.wellspring.example.angular.backend.persistence.domain.Product;
import org.wellspring.example.angular.backend.persistence.repository.ProductRepository;
import org.wellspring.example.angular.backend.service.ProductService;
import org.wellspring.example.angular.backend.util.ResourcePaths;

@RestController
@RequestMapping(value = ResourcePaths.PUBLIC_ROOT_API + ResourcePaths.Product.ROOT)
public class ProductController extends RestCrudControllerImpl<ProductService, ProductRepository, Product, Long> {

}
