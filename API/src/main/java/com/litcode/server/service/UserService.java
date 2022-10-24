package com.litcode.server.service;

import com.litcode.server.dto.SignInRequest;
import com.litcode.server.dto.UserProfile;

public interface UserService {
	UserProfile getUserInfo(SignInRequest signInInfo);
}
