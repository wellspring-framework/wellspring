package org.wellspring.crud.angular.example.service.impl;

import javax.annotation.Resource;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.wellspring.crud.angular.example.persistence.domain.AuthUser;
import org.wellspring.crud.angular.example.service.AuthUserService;

// TODO see http://www.sivalabs.in/2014/03/springmvc4-spring-data-jpa.html
// TODO http://bearprogrammer.com/2012/07/20/customizing-spring-security/
//TODO http://blog.techdev.de/testing-a-secured-spring-data-rest-service-with-java-8-and-mockmvc/
// TODO http://www.loiane.com/2010/01/tutorial-comecando-com-spring-security/
@Component
public class CustomUserDetailsService implements UserDetailsService {
	@Resource
	private AuthUserService authUserService;

	@Override
	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException {
		// TODO implement AuthUser findByUserName
		AuthUser user = authUserService.findByEmail(userName);
		if (user == null) {
			throw new UsernameNotFoundException("UserName " + userName
					+ " not found");
		}

		// return new AuthUser(user);
		return null;
	}
}
