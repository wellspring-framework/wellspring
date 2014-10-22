package org.wellspring.crud.angular.example.persistence.domain;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.Email;
import org.springframework.data.domain.Persistable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity(name = "auth_user")
public class AuthUser implements UserDetails, Persistable<String> {

	private static final long serialVersionUID = -642321391475413351L;

	@Id
	@Column(nullable = false, length = 50, unique = true)
	String username;

	@Column(nullable = false, length = 50)
	String password;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user")
	User user;

	@Email
	@Column(length = 100)
	private String email;

	Boolean accountNonExpired;

	Boolean accountNonLocked;

	Boolean credentialsNonExpired;

	Boolean enabled;

	/**
	 * relation to our roles
	 **/
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Authorities> roles = new HashSet<Authorities>();

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authList = new HashSet<GrantedAuthority>();

		for (Authorities role : roles) {
			authList.add(new SimpleGrantedAuthority(role.getRole().toString()));
		}

		// Return list of granted authorities
		return authList;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {

		return accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String getId() {
		return getUsername();
	}

	@Override
	public boolean isNew() {
		return null == getId();
	}

}
