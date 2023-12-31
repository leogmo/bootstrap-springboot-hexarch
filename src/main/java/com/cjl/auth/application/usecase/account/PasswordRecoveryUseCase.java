package com.cjl.auth.application.usecase.account;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import com.cjl.auth.application.usecase.AccountRepository;
import com.cjl.auth.application.usecase.EmailSender;
import com.cjl.auth.domain.User;
import com.cjl.auth.infrastructure.security.jwt.JwtTokenUtil;

import lombok.RequiredArgsConstructor;

@Component
public class PasswordRecoveryUseCase {
	@Autowired
	JwtTokenUtil jwtTokenUtil;
	
    private final AccountRepository accountRepository;
    private final EmailSender emailSender;
    
    

    public PasswordRecoveryUseCase(AccountRepository accountRepository,
			EmailSender emailSender) {
		super();
		this.accountRepository = accountRepository;
		this.emailSender = emailSender;
	}



	public void recover(PassRecoverDTO passRecoverDTO) throws Exception {
        Optional<User> user = accountRepository.findByEmail(passRecoverDTO.getEmail());
        if (!user.isPresent()){
            throw new Exception("Email não encontrado na base de dados");
        }
        String token = jwtTokenUtil.generateToken(user.get());

        emailSender.sendRecoveryEmail(token, user.get(), passRecoverDTO.getUrl());
    }
}
