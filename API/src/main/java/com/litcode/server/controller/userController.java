package com.litcode.server.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(controllerUtils.prefix + "/users")
public class userController {

  @PostMapping("/sign-in")
  private String signIn() {
    throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, "WAIT");
  }

  @PostMapping("/")
  private String signUp() {
    throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, "WAIT");
  }
}
