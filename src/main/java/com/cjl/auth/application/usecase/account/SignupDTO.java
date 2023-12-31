package com.cjl.auth.application.usecase.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class SignupDTO {
    private String username;
    private String email;
    private String password;
}
