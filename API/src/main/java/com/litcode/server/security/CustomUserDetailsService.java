package com.litcode.server.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.litcode.server.model.UserModel;
import com.litcode.server.repository.UserRepository;

public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		UserModel userModel = userRepository.findByUserId(username)
				.orElseThrow(() -> new UsernameNotFoundException("NOT FOUND USERNAME: " + username));

		return new UserPrincipal(userModel);
	}
}
