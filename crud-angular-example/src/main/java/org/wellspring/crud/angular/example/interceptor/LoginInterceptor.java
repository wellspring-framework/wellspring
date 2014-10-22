package org.wellspring.crud.angular.example.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.wellspring.crud.angular.example.persistence.domain.AuthUser;
import org.wellspring.crud.angular.example.service.AuthUserService;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private AuthUserService authUserService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();

		AuthUser user = (AuthUser) session.getAttribute("user");
		if (user == null) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String email = auth.getName();
			user = authUserService.findByEmail(email);
			session.setAttribute("user", user);
		}

		return super.preHandle(request, response, handler);
	}
}