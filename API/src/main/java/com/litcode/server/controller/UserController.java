package com.litcode.server.controller;

import com.litcode.server.dto.UserProfile;
import com.litcode.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(ControllerUtils.prefix + "/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/check")
	private ResponseEntity<UserProfile> tokenCheck(Authentication authentication) {
		return ResponseEntity.ok().body(userService.getUserInfoByUserId(authentication.getName()));
	}
}
