package com.litcode.server.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.litcode.server.model.ProblemInProgress;

@Repository
public interface ProblemInProgressRepository extends JpaRepository<ProblemInProgress, Long> {
	// Optional<ProblemInProgress> findByProblemIdAndUserIdAndLang(Integer
	// problemId, String userId, String language);

	Optional<ProblemInProgress> findByProblemIdAndUserIdAndLanguage(Integer problemId, Integer userId, String language);

	Optional<List<ProblemInProgress>> findAllByProblemIdAndUserId(Integer problemId, Integer userId);
}
