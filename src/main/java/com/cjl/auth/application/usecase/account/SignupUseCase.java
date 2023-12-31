package com.cjl.auth.application.usecase.account;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import com.cjl.auth.application.usecase.AccountRepository;
import com.cjl.auth.domain.User;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SignupUseCase {
    private final AccountRepository accountRepository;

    public User signup(SignupDTO signupDTO) throws Exception {
        return accountRepository.signup(signupDTO);
    }
}
