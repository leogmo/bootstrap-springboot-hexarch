package com.cjl.auth.application.usecase.account;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseDTO {
	private final String jwttoken;
}
