package com.litcode.server.dto;

import java.io.Serializable;
import java.util.List;

import com.litcode.server.model.SkeletonCode;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
public class ProblemResponse implements Serializable {

	private String description;
	private String sampleInput;
	private List<SkeletonCode> skeletonCodeList;

	@Builder
	public ProblemResponse(
			@NonNull String description,
			@NonNull String sampleInput,
			@NonNull List<SkeletonCode> skeletonCodeList) {
		this.description = description;
		this.sampleInput = sampleInput;
		this.skeletonCodeList = skeletonCodeList;
	}
}
