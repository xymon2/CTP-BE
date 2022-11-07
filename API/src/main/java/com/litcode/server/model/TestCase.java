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

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Data
@Entity
@Table(name = "test_cases", uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }) })
public class TestCase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "problem_id", referencedColumnName = "id")
	private Problem problem;

	@Column(name = "input", columnDefinition = "TEXT", nullable = false)
	private String input;

	@Column(name = "output", columnDefinition = "TEXT", nullable = false)
	private String output;

	@Column(name = "score")
	private Integer score;
}
