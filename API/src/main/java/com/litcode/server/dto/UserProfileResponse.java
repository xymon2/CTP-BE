package com.litcode.server.dto;

import java.io.Serializable;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
public class UserProfileResponse implements Serializable {

	private String accessToken;
	private String userId;
	private String email;
	private List<Object> solvedId;

	@Builder
	public UserProfileResponse(
			@NonNull String accessToken,
			@NonNull String userId,
			@NonNull String email,
			@NonNull List<Object> solvedId) {
		this.accessToken = accessToken;
		this.userId = userId;
		this.email = email;
		this.solvedId = solvedId;
	}
}