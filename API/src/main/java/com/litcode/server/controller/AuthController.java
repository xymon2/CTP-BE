package com.litcode.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.litcode.server.dto.SignInRequest;
import com.litcode.server.security.JwtTokenProvider;

@RestController
@RequestMapping(ControllerUtils.prefix + "/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@PostMapping("")
	public String signIn(@RequestBody SignInRequest signInInfo) {

		try {
			// Check id and password are valid
			Authentication authentication = authenticationManager
					.authenticate(
							new UsernamePasswordAuthenticationToken(signInInfo.getId(),
									signInInfo.getPassword()));

			// Save authentication info to security context
			// And make jwt token
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwtToken = jwtTokenProvider.generateToken(signInInfo.getId());
			return jwtToken;

		} catch (BadCredentialsException ex) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "WRONG_USER_INFO");
		} catch (Exception ex) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR");
		}

	}
}
