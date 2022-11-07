package com.litcode.server.dto;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
public class ProblemResponse implements Serializable {

	private Integer id;
	private String name;
	private String description;
	private String sampleInput;

	@Builder
	public ProblemResponse(
			@NonNull Integer id,
			@NonNull String name,
			@NonNull String description,
			@NonNull String sampleInput) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.sampleInput = sampleInput;
	}
}
