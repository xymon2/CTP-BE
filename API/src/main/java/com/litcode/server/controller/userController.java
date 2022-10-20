package com.litcode.server.controller;

import com.litcode.server.payload.SignInRequest;
import com.litcode.server.payload.UserProfile;
import com.litcode.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

	@PostMapping("/sign-in")
	public String signIn(@RequestBody SignInRequest signInInfo) {
		UserProfile ret = userService.getUserInfo(signInInfo);
		return "sd";
	}

	@PostMapping("/")
	public String signUp() {
		throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, "WAIT");
	}
}
