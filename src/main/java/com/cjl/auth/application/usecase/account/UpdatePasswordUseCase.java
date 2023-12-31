package com.cjl.auth.application.usecase.account;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import com.cjl.auth.application.usecase.AccountRepository;
import com.cjl.auth.domain.User;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UpdatePasswordUseCase {
    private final AccountRepository accountRepository;

    public void update(UpdatePasswordDTO updatePasswordDTO) throws Exception {
    	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
    	
        Optional<User> user = accountRepository.findByEmail(updatePasswordDTO.getEmail());
        if (!user.isPresent()) {
            throw new Exception("Email não encontrado na base de dados");
        }

        user.get().setPassword(encoder.encode(updatePasswordDTO.getPassword()));
        accountRepository.save(user.get());
    }
}
