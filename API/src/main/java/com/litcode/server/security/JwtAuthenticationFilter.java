package com.litcode.server.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.AuthenticationException;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.authentication.AuthenticationManager;

// import lombok.extern.slf4j.Slf4j;

// @Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
	// @Autowired
	// private JwtTokenProvider tokenProvider;

	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			LOGGER.error("dasdas");

			String jwtToken = getJwtFromRequest(request);
			System.out.println(jwtToken);
		} catch (Exception ex) {

		}
	}

	private String getJwtFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7, bearerToken.length());
		}
		return null;
	}
}
