package com.cjl.auth.application.usecase.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class PassRecoverDTO {
    private String email;
    private String url;
}
