package org.wellspring.crud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wellspring.crud.controller.impl.RestCrudControllerImpl;
import org.wellspring.crud.persistence.domain.Product;
import org.wellspring.crud.persistence.repository.impl.ProductRepository;
import org.wellspring.crud.service.ProductService;
import org.wellspring.crud.util.ResourcePaths;

@Controller
@RequestMapping(ResourcePaths.Product.ROOT)
public class ProductController extends
		RestCrudControllerImpl<ProductService, ProductRepository, Product, Long> {

}
