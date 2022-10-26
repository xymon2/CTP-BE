package com.litcode.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.litcode.server.model.ProblemModel;
import com.litcode.server.repository.ProblemRepository;

@RestController
@RequestMapping(ControllerUtils.prefix + "/problems")
public class ProblemController {

	@Autowired
	ProblemRepository problemRepository;

	@GetMapping("/{id}")
	private String getOneProblem() {
		// throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, "WAIT");
		ProblemModel a = problemRepository.findById(1).orElseThrow();

		return a.getDescription();
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