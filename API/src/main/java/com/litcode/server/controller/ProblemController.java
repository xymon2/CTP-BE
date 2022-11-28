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

import com.litcode.server.dto.ProblemResponse;
import com.litcode.server.dto.ProblemRunRequest;
import com.litcode.server.dto.ProblemRunResponse;
import com.litcode.server.dto.ProblemSummary;
import com.litcode.server.model.ProblemInProgress;
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
	private ResponseEntity<ProblemResponse> getOneProblemWithNoTestCases(@PathVariable(name = "id") Integer id) {
		return ResponseEntity.ok().body(problemService.getOneProblemWithNoTestCases(id));
	}

	@GetMapping("/{id}/progress")
	private ResponseEntity<List<ProblemInProgress>> getOneInProgressCode(Authentication authentication,
			@PathVariable(name = "id") Integer id) {
		return ResponseEntity.ok().body(problemService.getOneProblemInProgress(id, authentication.getName()));
	}

	@PostMapping("/{id}/run")
	private ResponseEntity<ProblemRunResponse> runCode(Authentication authentication,
			@PathVariable(name = "id") Integer id,
			@RequestBody ProblemRunRequest runReq) {
		ProblemRunResponse res = problemService.runCode(id, authentication.getName(),
				runReq.getCode(),
				runReq.getInput(),
				runReq.getLanguage());

		return ResponseEntity.ok().body(res);
	}

	@PostMapping("/{id}/submit")
	private String submitCode() {
		throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, "WAIT");
	}
}