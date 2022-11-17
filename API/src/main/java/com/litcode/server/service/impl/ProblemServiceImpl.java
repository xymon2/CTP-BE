package com.litcode.server.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.litcode.server.dto.ProblemResponse;
import com.litcode.server.dto.ProblemRunResponse;
import com.litcode.server.dto.ProblemSummary;
import com.litcode.server.grpcClient.GrpcClient;
import com.litcode.server.model.Problem;
import com.litcode.server.model.ProblemInProgress;
import com.litcode.server.model.User;
import com.litcode.server.repository.ProblemInProgressRepository;
import com.litcode.server.repository.ProblemRepository;
import com.litcode.server.repository.UserRepository;
import com.litcode.server.service.ProblemService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProblemServiceImpl implements ProblemService {

	@Autowired
	ProblemRepository problemRepository;

	@Autowired
	ProblemInProgressRepository problemInProgressRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	GrpcClient grpcClient;

	public List<ProblemSummary> getAllProblems() {

		List<Problem> problems = problemRepository.findAll();

		List<ProblemSummary> problemList = new ArrayList<ProblemSummary>();
		for (Problem problem : problems) {
			problemList.add(new ProblemSummary(problem.getId(), problem.getName()));
		}

		return problemList;
	}

	public ProblemResponse getOneProblemWithNoTestCases(Integer id) {
		Problem problem = problemRepository.findById(id).orElseThrow();
		ProblemResponse probWithNoTestCases = new ProblemResponse(problem.getDescription(), problem.getSampleInput(),
				problem.getSkeletonCodeList());
		return probWithNoTestCases;
	}

	public List<ProblemInProgress> getOneProblemInProgress(Integer problemId, String userId) {

		User user = userRepository.findByUserId(userId).orElseThrow();
		List<ProblemInProgress> pip = problemInProgressRepository.findAllByProblemIdAndUserId(problemId, user.getId())
				.orElseThrow();

		return pip;
	}

	public ProblemRunResponse runCode(Integer probId, String userId, String code, String input, String language) {
		User user = userRepository.findByUserId(userId).orElseThrow();
		Problem problem = problemRepository.findById(probId).orElseThrow();

		ProblemInProgress problemIP = problemInProgressRepository
				.findByProblemIdAndUserIdAndLanguage(problem.getId(), user.getId(), language).orElseGet(
						() -> {
							ProblemInProgress pip = new ProblemInProgress();
							pip.setProblem(problem);
							pip.setUser(user);
							pip.setLanguage(language);
							return pip;
						});

		problemIP.setCode(code);
		problemInProgressRepository.save(problemIP);
		return grpcClient.runCode(code, input, language);
	}

	public String submitCode() {
		return "submit";
	}

}
