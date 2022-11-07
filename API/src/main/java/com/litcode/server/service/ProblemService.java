package com.litcode.server.service;

import java.util.List;

import com.litcode.server.dto.ProblemSummary;
import com.litcode.server.model.Problem;

public interface ProblemService {
	Problem getOneProblem(Integer id);

	List<ProblemSummary> getAllProblems();

	String runCode(String userId, String code, String input, String language);

	String submitCode();
}