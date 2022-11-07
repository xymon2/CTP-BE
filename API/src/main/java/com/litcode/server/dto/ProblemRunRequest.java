package com.litcode.server.dto;

import lombok.Data;

@Data
public class ProblemRunRequest {
	public String language;
	public String code;
	public String input;
}
