package com.litcode.server.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.litcode.server.dto.ProblemSummary;
import com.litcode.server.model.Problem;
import com.litcode.server.repository.ProblemRepository;
import com.litcode.server.service.ProblemService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProblemServiceImpl implements ProblemService {

	@Autowired
	ProblemRepository problemRepository;

	public List<ProblemSummary> getAllProblems() {

		List<Problem> problems = problemRepository.findAll();

		List<ProblemSummary> problemList = new ArrayList<ProblemSummary>();
		for (Problem problem : problems) {

			problemList.add(new ProblemSummary(problem.getId(), problem.getName()));
		}

		return problemList;
	}

	public Problem getOneProblem(Integer id) {

		Problem problem = problemRepository.findById(1).orElseThrow();

		return problem;
	}

	public String runCode() {
		return "run";
	}

	public String submitCode() {
		return "submit";
	}
}
