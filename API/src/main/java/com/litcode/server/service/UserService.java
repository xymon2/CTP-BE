package com.litcode.server.service;

import com.litcode.server.payload.SignInRequest;
import com.litcode.server.payload.UserProfile;

public interface UserService {
	UserProfile getUserInfo(SignInRequest signInInfo);
}
