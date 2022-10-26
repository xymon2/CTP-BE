package com.litcode.server.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(ControllerUtils.prefix + "/codes")
public class CodeController {

	@GetMapping("/")
	private String getCode() {
		throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, "WAIT");
	}

	@PostMapping("/run")
	private String runCode() {
		throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, "WAIT");
	}

	@PostMapping("/submit")
	private String submitCode() {
		throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, "WAIT");
	}
}