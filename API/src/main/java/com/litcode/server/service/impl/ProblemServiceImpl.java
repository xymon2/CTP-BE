package com.litcode.server.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

	public Problem getOneProblem(Integer id) {
		Problem problem = problemRepository.findById(1).orElseThrow();
		return problem;
	}

	public ProblemInProgress getOneProblemInProgress(Integer problemId, String userId) {

		User user = userRepository.findByUserId(userId).orElseThrow();
		ProblemInProgress pip = problemInProgressRepository.findAllByProblemIdAndUserId(problemId, user.getId())
				.orElseThrow();

		return pip;
	}

	public ProblemRunResponse runCode(Integer probId, String userId, String code, String input, String language) {

		log.info(userId);

		log.info("grpc start");

		User user = userRepository.findByUserId(userId).orElseThrow();
		Problem problem = problemRepository.findById(probId).orElseThrow();

		ProblemInProgress pip = new ProblemInProgress();
		pip.setLanguage(language);
		pip.setCode(code);
		pip.setProblem(problem);
		pip.setUser(user);

		problemInProgressRepository.save(pip);
		return grpcClient.runCode(code, input, language);
	}

	public String submitCode() {
		return "submit";
	}

}
