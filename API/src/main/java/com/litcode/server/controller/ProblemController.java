package com.litcode.server.controller;

import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.litcode.server.model.Problem;
import com.litcode.server.model.SkeletonCode;
import com.litcode.server.repository.ProblemRepository;

@RestController
@RequestMapping(ControllerUtils.prefix + "/problems")
public class ProblemController {

	@Autowired
	ProblemRepository problemRepository;

	@GetMapping("/{id}")
	private String getOneProblem() {
		// throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, "WAIT");
		Problem a = problemRepository.findById(1).orElseThrow();

		ListIterator<SkeletonCode> b = a.getSkeletonCodeList().listIterator();

		while (b.hasNext()) {
			SkeletonCode c = b.next();
			System.out.println(c.getCode());
		}

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