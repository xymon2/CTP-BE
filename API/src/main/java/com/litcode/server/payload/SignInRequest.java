package com.litcode.server.payload;

import lombok.Data;

@Data
public class SignInRequest {
	public String id;
	public String password;
}
