package com.litcode.server.security;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.litcode.server.model.UserModel;
import com.litcode.server.repository.UserRepository;

// make an userDetails object for auth
@Service
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		UserModel userModel = userRepository.findByUserId(username)
				.orElseThrow(() -> new UsernameNotFoundException("NOT FOUND USERNAME: " +
						username));

		return new CustomUserDetails(userModel);
	}
}

class CustomUserDetails implements UserDetails {

	private UserModel userModel;

	public CustomUserDetails(UserModel userModel) {
		this.userModel = userModel;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return userModel.getPassword();
	}

	@Override
	public String getUsername() {
		return userModel.getUserId();
	}

	public String getUserEmail() {
		return userModel.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
}
