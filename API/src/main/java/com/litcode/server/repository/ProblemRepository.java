package com.litcode.server.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.litcode.server.model.Problem;

@Repository
public interface ProblemRepository extends JpaRepository<Problem, Long> {
	Optional<Problem> findById(Integer id);
}
