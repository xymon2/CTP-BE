package com.litcode.server.security;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
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
				.antMatchers(HttpMethod.POST,
						"/")
				.permitAll()
				.anyRequest().authenticated()
				.and()
				.addFilterBefore(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

		http.headers().frameOptions().sameOrigin();

		return http.build();
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		System.out.println("aaaaa");
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