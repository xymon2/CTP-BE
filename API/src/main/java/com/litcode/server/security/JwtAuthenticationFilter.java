package com.litcode.server.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.authentication.AuthenticationManager;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
	}
	// private final AuthenticationManager authenticationManager;
	// private UserRepository userRepository;

	// @Override
	// public Authentication attemptAuthentication(HttpServletRequest request,
	// HttpServletResponse response)
	// throws AuthenticationException {
	// String userid = request.getParameter("id");
	// String password = request.getParameter("password");

	// UsernamePasswordAuthenticationToken authenticationToken = new
	// UsernamePasswordAuthenticationToken(userid,
	// password);
	// return authenticationManager.authenticate(authenticationToken);
	// }
}
