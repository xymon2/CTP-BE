package com.litcode.server.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Data;

@Data
@Entity
@Table(name = "problems", uniqueConstraints = { @UniqueConstraint(columnNames = { "id", "name" }) })
public class ProblemModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "description", columnDefinition = "TEXT", nullable = false)
	private String description;

	@Column(name = "sample_input", columnDefinition = "TEXT", nullable = false)
	private String sampleInput;
}