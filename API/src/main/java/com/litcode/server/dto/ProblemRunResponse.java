package com.litcode.server.dto;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
public class ProblemRunResponse implements Serializable {
	public String output;
	public String stdout;

	@Builder
	public ProblemRunResponse(
			@NonNull String output,
			@NonNull String stdout) {
		this.output = output;
		this.stdout = stdout;
	}
}
