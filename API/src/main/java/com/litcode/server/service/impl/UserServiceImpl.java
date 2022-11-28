package com.litcode.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.BasicJsonParser;
import org.springframework.boot.json.JsonParser;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.litcode.server.dto.SignInRequest;
import com.litcode.server.dto.UserProfile;
import com.litcode.server.model.User;
import com.litcode.server.repository.UserRepository;
import com.litcode.server.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	public UserProfile getUserInfoByUserId(String userId) {
		User user = userRepository.findByUserId(userId)
				.orElseThrow();

		String solvedIdString = user.getSolvedId();
		JsonParser jsonParser = new BasicJsonParser();
		List<Object> solvedId = jsonParser.parseList(solvedIdString);

		UserProfile userProfile = UserProfile.builder()
				.userId(user.getUserId())
				.name(user.getName())
				.email(user.getEmail())
				.solvedId(solvedId)
				.build();

		return userProfile;
	}

}
