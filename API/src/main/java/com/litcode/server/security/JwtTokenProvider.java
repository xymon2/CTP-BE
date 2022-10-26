package com.litcode.server.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class JwtTokenProvider {
	private String jwtSecret = "dasdasddsadasdasdasdasgnfsdkgsgsfdklgnfdnmekehwdkfhesdfhsfhksdhfksdfnv";
	private Key jwtKey;
	private int jwtExpirationInMs = 3600000;

	// decode jwt secret string to key
	@PostConstruct
	protected void init() {
		byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
		this.jwtKey = Keys.hmacShaKeyFor(keyBytes);
	}

	public String generateToken(Authentication authentication) {
		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

		return Jwts.builder()
				.setIssuedAt(new Date())
				.claim("userId", userPrincipal.getUsername())
				.setExpiration(expiryDate)
				.signWith(jwtKey)
				.compact();
	}

	public String getUserIdFromJWT(String jwtToken) {

		Claims claims = Jwts.parserBuilder()
				.setSigningKey(jwtKey)
				.build()
				.parseClaimsJws(jwtToken)
				.getBody();

		return claims.get("userId").toString();

	}

	public boolean validateToken(String authToken) {

		try {
			Jwts.parserBuilder().setSigningKey(jwtKey).build().parseClaimsJws(authToken);
			return true;
		} catch (SecurityException ex) {
			// LOGGER.error("Invalid JWT signature");
			System.out.println("invalid jwt sig");
		} catch (MalformedJwtException ex) {
			// LOGGER.error("Invalid JWT token");
			System.out.println("invalid jwt tok");

		} catch (ExpiredJwtException ex) {
			// LOGGER.error("Expired JWT token");
			System.out.println("invalid jwt exp");
		} catch (UnsupportedJwtException ex) {
			System.out.println("invalid jwt sup");
		} catch (IllegalArgumentException ex) {
			System.out.println("invalid jwt empt");
			// LOGGER.error("JWT claims string is empty");
		}
		return false;
	}
}
