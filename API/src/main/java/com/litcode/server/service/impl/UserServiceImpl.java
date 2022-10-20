package com.litcode.server.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.BasicJsonParser;
import org.springframework.boot.json.JsonParser;
import org.springframework.stereotype.Service;

import com.litcode.server.model.UserModel;
import com.litcode.server.payload.SignInRequest;
import com.litcode.server.payload.UserProfile;
import com.litcode.server.repository.UserRepository;
import com.litcode.server.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	public UserProfile getUserInfo(SignInRequest signInInfo) {
		UserModel user = userRepository.findByUserIdAndPassword(signInInfo.getId(), signInInfo.getPassword());

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
