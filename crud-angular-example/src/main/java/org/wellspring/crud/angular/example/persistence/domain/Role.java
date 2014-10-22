package org.wellspring.crud.angular.example.persistence.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity(name = "auth_role")
public class Role extends AbstractPersistable<Long> {
	private static final long serialVersionUID = 5008042705233935598L;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, unique = true)
	private RoleEnum role;

	public RoleEnum getRole() {
		return role;
	}

	public void setRole(RoleEnum role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return new ReflectionToStringBuilder(this).toString();
	}
}