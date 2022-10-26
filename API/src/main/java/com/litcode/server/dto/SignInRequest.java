package com.litcode.server.dto;

import lombok.Data;

@Data
public class SignInRequest {
	public String id;
	public String password;
}
