package com.litcode.server.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Data
@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = { "user_id" }) })
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "user_id", nullable = false)
	private String userId;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "solved_id", columnDefinition = "json")
	private String solvedId;

	// @Column(
	// name = "created",
	// columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
	// )
	@CreationTimestamp
	private Timestamp created;

	// @Column(
	// name = "updated",
	// columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE
	// CURRENT_TIMESTAMP"
	// )
	@UpdateTimestamp
	private Timestamp updated;

	@JsonManagedReference
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ProblemInProgress> problemInProgressList;

	@JsonManagedReference
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ProblemSolved> problemSolvedList;

	public User orElseThrow(Object object) {
		return null;
	}

}
