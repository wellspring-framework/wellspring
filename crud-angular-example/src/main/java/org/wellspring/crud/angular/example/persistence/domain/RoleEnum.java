package org.wellspring.crud.angular.example.persistence.domain;

public enum RoleEnum {

	ADMIN("ADMIN"), STAFF("STAFF"), USER("USER"), GUEST("GUEST");

	private String role;

	private RoleEnum(String role) {
		this.role = role;
	}

	public String getRole() {
		return role;
	}

	@Override
	public String toString() {
		return role;
	}

}