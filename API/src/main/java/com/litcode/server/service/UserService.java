package com.litcode.server.service;

import com.litcode.server.dto.UserProfile;

public interface UserService {
	UserProfile getUserInfoByUserId(String userId);
}
