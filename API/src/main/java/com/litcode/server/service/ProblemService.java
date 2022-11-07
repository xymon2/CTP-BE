package com.litcode.server.service;

import java.util.List;

import com.litcode.server.dto.ProblemSummary;
import com.litcode.server.model.Problem;

public interface ProblemService {
	Problem getOneProblem(Integer id);

	List<ProblemSummary> getAllProblems();

	String runCode();

	String submitCode();
}