package com.litcode.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.BasicJsonParser;
import org.springframework.boot.json.JsonParser;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.litcode.server.dto.SignInRequest;
import com.litcode.server.dto.UserProfile;
import com.litcode.server.model.UserModel;
import com.litcode.server.repository.UserRepository;
import com.litcode.server.security.JwtTokenProvider;
import com.litcode.server.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	UserRepository userRepository;

	public UserProfile getUserInfo(SignInRequest signInInfo) {
		UserModel user = userRepository.findByUserId(signInInfo.getId())
				.orElseThrow(() -> new UsernameNotFoundException("NOT FOUND USERNAME: " +
						signInInfo.getId()));

		String solvedIdString = user.getSolvedId();
		JsonParser jsonParser = new BasicJsonParser();

		List<Object> solvedId = jsonParser.parseList(solvedIdString);

		UserProfile userProfile = UserProfile.builder()
				.id(user.getId())
				.userId(user.getUserId())
				.email(user.getEmail())
				.solvedId(solvedId)
				.build();

		return userProfile;
	}

}
