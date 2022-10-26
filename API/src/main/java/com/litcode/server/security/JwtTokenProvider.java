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
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import javax.annotation.PostConstruct;

@Slf4j
@Component
public class JwtTokenProvider {
	// TODO
	// env var
	private String jwtSecret = "dasdasddsadasdasdasdasgnfsdkgsgsfdklgnfdnmekehwdkfhesdfhsfhksdhfksdfnv";
	private Key jwtKey;
	private int jwtExpirationInMs = 3600000;

	// decode jwt secret string to key
	@PostConstruct
	protected void init() {
		byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
		this.jwtKey = Keys.hmacShaKeyFor(keyBytes);
	}

	public String generateToken(String userId) {
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

		return Jwts.builder()
				.setIssuedAt(new Date())
				.claim("userId", userId)
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

		// TODO
		// error message handling according to validation error
		try {
			Jwts.parserBuilder().setSigningKey(jwtKey).build().parseClaimsJws(authToken);
			return true;
		} catch (SecurityException ex) {
			log.error("invalid jwt sig");
		} catch (MalformedJwtException ex) {
			log.error("invalid jwt tok");
		} catch (ExpiredJwtException ex) {
			log.error("invalid jwt exp");
		} catch (UnsupportedJwtException ex) {
			log.error("invalid jwt sup");
		} catch (IllegalArgumentException ex) {
			log.error("invalid jwt empt");
		}
		return false;
	}
}
