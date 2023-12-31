package com.cjl.auth.application.usecase;

import com.cjl.auth.application.usecase.account.SignupDTO;
import com.cjl.auth.domain.User;

import java.util.Optional;

public interface AccountRepository {
    Optional<User> findByEmail(String username);
    User signup(SignupDTO signupDTO) throws Exception;

    void save(User user) throws Exception;
}
