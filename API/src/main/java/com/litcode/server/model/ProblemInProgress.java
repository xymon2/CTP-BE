package com.litcode.server.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;

@Data
@Entity
@Table(name = "problems_in_progress", uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }) })
public class ProblemInProgress {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "problem_id", referencedColumnName = "id")
	private Problem problem;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	@Column(name = "language", columnDefinition = "TEXT", nullable = false)
	private String language;

	@Column(name = "code", columnDefinition = "TEXT")
	private String code;
}