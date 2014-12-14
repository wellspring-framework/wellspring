package org.wellspring.example.angular.backend.persistence.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "product")
public class Product extends AbstractPersistable<Long> {

	private static final long serialVersionUID = -451960778705144607L;

	@Column(nullable = false, length = 50)
	@NotEmpty
	private String name;

	@Column(nullable = false)
	@NotNull
	private BigDecimal price;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
