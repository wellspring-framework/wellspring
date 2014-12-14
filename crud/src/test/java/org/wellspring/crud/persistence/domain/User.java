package org.wellspring.crud.persistence.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.jpa.domain.AbstractAuditable;
import org.wellspring.crud.persistence.listener.EntityListener;

@Entity(name = "user")
@EntityListeners(value = { EntityListener.class })
public class User extends AbstractAuditable<User, Long> {

	private static final long serialVersionUID = 1L;

	@Column
	@NotEmpty
	private String name;

	@Column
	@NotEmpty
	private String lastName;

	@Email
	@Column
	@NotEmpty
	private String email;

	public String getEmail() {
		return email;
	}

	public String getLastName() {
		return lastName;
	}

	public String getName() {
		return name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setName(String name) {
		this.name = name;
	}

}
