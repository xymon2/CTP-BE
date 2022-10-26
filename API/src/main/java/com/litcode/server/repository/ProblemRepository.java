package com.litcode.server.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.litcode.server.model.ProblemModel;

@Repository
public interface ProblemRepository extends JpaRepository<ProblemModel, Long> {
	Optional<ProblemModel> findById(Integer id);
}
