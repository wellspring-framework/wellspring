package org.wellspring.crud.service.impl;

import org.springframework.stereotype.Service;
import org.wellspring.crud.persistence.domain.Product;
import org.wellspring.crud.persistence.repository.impl.ProductRepository;
import org.wellspring.crud.service.ProductService;

@Service
public class ProductServiceImpl extends CrudServiceImpl<ProductRepository, Product, Long>implements
		ProductService {

	/*
	 * @Autowired public CityServiceImpl(CityRepository repository) {
	 * super(repository); }
	 */
}
