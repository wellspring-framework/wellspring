package org.wellspring.crud.persistence.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotEmpty;

@Entity(name = "user")
public class User extends BasicEntity {

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
