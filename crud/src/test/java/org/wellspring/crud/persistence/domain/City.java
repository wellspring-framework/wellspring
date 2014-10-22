package org.wellspring.crud.persistence.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.jpa.domain.AbstractAuditable;

@Entity(name = "city")
public class City extends AbstractAuditable<User, Long> {

	private static final long serialVersionUID = 1L;

	@Column
	@NotEmpty
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}