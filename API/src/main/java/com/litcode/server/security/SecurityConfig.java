package com.litcode.server.security;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.cors().configurationSource(corsConfigurationSource()).and()
				// For cookie auth
				.csrf().disable()
				// Error handling in security filter
				// .exceptionHandling()
				// .accessDeniedHandler(null)
				// .authenticationEntryPoint(null)
				// .and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.authorizeRequests()
				.antMatchers("/users/sign-in").permitAll()
				.anyRequest().authenticated()
				.and()
				.addFilter(new JwtAuthenticationFilter());

		http.headers().frameOptions().sameOrigin();

		return http.build();
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		// whether allow or not the cookie from cors origin for auth
		configuration.setAllowCredentials(false);
		// allowed cors origin
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080"));
		// allowed cors methods
		configuration.setAllowedMethods(Arrays.asList("*"));
		configuration.addAllowedHeader("*");

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;

	}
}