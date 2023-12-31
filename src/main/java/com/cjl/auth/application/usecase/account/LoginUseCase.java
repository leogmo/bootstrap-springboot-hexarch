package com.cjl.auth.application.usecase.account;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import com.cjl.auth.application.usecase.AccountRepository;
import com.cjl.auth.domain.User;
import com.cjl.auth.infrastructure.security.jwt.JwtTokenUtil;

import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class LoginUseCase {
	
	@Autowired
	JwtTokenUtil jwtTokenUtil;
	
    private final AccountRepository accountRepository;
    
    public LoginResponseDTO login(LoginDTO loginDTO) {
    	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
    	
        Optional<User> user = accountRepository.findByEmail(loginDTO.getEmail());

        if (user.isPresent()){
            if (user.get().matchesPassword(encoder.encode(loginDTO.getPassword()))){
                try {
                    return new LoginResponseDTO(jwtTokenUtil.generateToken(user.get()));
                } catch (Exception e){
                    throw new SecurityException("Erro ao gerar o token");
                }
            }
        } else {
            throw new SecurityException("Usuário não encontrado");
        }
        return null;
    }
}
