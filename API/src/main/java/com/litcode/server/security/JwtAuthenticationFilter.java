package com.litcode.server.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {

			String jwtToken = getJwtTokenFromRequest(request);

			if (StringUtils.hasText(jwtToken) &&
					jwtTokenProvider.validateToken(jwtToken)) {
				// if (StringUtils.hasText(jwtToken)) {
				log.info(jwtToken);
				jwtTokenProvider.getUserIdFromJWT(jwtToken);

			}

		} catch (Exception ex) {
			System.out.println(ex);

		}

		filterChain.doFilter(request, response);
	}

	private String getJwtTokenFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7, bearerToken.length());
		}
		return null;
	}
}
