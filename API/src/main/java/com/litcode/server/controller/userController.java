package com.litcode.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;
import com.litcode.server.service.UserService;

class signInInfo{
  public String id;
  public String password;
}

@RestController
@RequestMapping(ControllerUtils.prefix + "/users")
public class UserController {
	@Autowired
	private UserService userService;

  @PostMapping("/sign-in")
  public String signIn(@RequestBody signInInfo signInInfo) {
    String ret = userService.signIn();
    return ret;
  }

  @PostMapping("/")
  public String signUp() {
    throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, "WAIT");
  }
}
