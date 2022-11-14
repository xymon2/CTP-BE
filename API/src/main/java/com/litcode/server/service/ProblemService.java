package com.litcode.server.service;

import java.util.List;

import com.litcode.server.dto.ProblemRunResponse;
import com.litcode.server.dto.ProblemSummary;
import com.litcode.server.model.Problem;
import com.litcode.server.model.ProblemInProgress;

public interface ProblemService {
	Problem getOneProblem(Integer id);

	List<ProblemSummary> getAllProblems();

	ProblemRunResponse runCode(Integer probId, String userId, String code, String input, String language);

	String submitCode();

	List<ProblemInProgress> getOneProblemInProgress(Integer problemId, String userId);
}