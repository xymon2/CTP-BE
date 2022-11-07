package com.litcode.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.litcode.server.dto.ProblemRunRequest;
import com.litcode.server.dto.ProblemSummary;
import com.litcode.server.model.Problem;
import com.litcode.server.repository.ProblemRepository;
import com.litcode.server.service.ProblemService;

@RestController
@RequestMapping(ControllerUtils.prefix + "/problems")
public class ProblemController {

	@Autowired
	ProblemRepository problemRepository;

	@Autowired
	ProblemService problemService;

	@GetMapping("")
	private ResponseEntity<List<ProblemSummary>> getAllProblem() {
		return ResponseEntity.ok().body(problemService.getAllProblems());
	}

	@GetMapping("/{id}")
	private ResponseEntity<Problem> getOneProblem(@PathVariable(name = "id") Integer id) {
		return ResponseEntity.ok().body(problemService.getOneProblem(id));
	}

	@PostMapping("/run")
	private String runCode(Authentication authentication, @RequestBody ProblemRunRequest runReq) {
		return problemService.runCode(authentication.getName(), runReq.getCode(), runReq.getInput(),
				runReq.getLanguage());
	}

	@PostMapping("/submit")
	private String submitCode() {
		throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, "WAIT");
	}
}