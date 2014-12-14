package org.wellspring.example.angular.backend;

import java.math.BigDecimal;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;
import org.wellspring.example.angular.backend.persistence.domain.Product;
import org.wellspring.example.angular.backend.persistence.domain.User;
import org.wellspring.example.angular.backend.service.ProductService;
import org.wellspring.example.angular.backend.service.UserService;

@Configuration
public class LoadDataConfiguration {

	String[] products = { "Bike", "Camera", "Car", "pendrive", "Pencil", "Chair", "Smartphone", "Notebook", "Earphone", "Tablet", "Motocicle", "Bag", "Dress", "T-Shirt" };

	String[] users = { "John", "Mary", "Joseph", "Paul", "Hugo", "Carl", "Melissa", "Nicole", "James", "Lucie", "Charlie", "Nathan", "Henry", "Anne", "Julie", "Isal", "Alex", "Carol", "Christine",
			"Erick", "Josie", "Raphael", "Adam", "Adele", "Barbie", "Ken", "Steve", "Roger", "Betty", "Billy", "Calvin", "Carly", "Johnson", "Cassie", "Debbie", "Deborah", "Eugene", "Everard",
			"Isabelle", "Jane", "Jason", "Fredie", "Jennifer" };

	@Resource
	private ProductService productService;

	@Resource
	private UserService userService;

	@PostConstruct
	public void init() {
		createProducts();
		createUsers();
	}

	private void createProducts() {

		for (int i = 0; i < products.length; i++) {
			Product product = new Product();
			product.setName(products[i]);
			product.setPrice(generateRandomBigDecimalFromRange(BigDecimal.ZERO, BigDecimal.TEN));
			productService.save(product);
		}

	}

	private void createUsers() {
		for (int i = 0; i < users.length; i++) {
			User user = new User();
			user.setName(users[i]);
			user.setEmail(user.getName().toLowerCase() + "@test.com");
			userService.save(user);
		}
	}

	private BigDecimal generateRandomBigDecimalFromRange(BigDecimal min, BigDecimal max) {
		BigDecimal randomBigDecimal = min.add(new BigDecimal(Math.random()).multiply(max.subtract(min)));
		return randomBigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

}
