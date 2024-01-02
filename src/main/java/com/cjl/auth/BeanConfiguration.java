package com.cjl.auth;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.cjl.auth.adapters.out.jpa.AccountJpaRepositoryAdapter;
import com.cjl.auth.adapters.out.mail.EmailSenderAdapter;
import com.cjl.auth.application.usecase.account.LoginUseCase;
import com.cjl.auth.application.usecase.account.PasswordRecoveryUseCase;
import com.cjl.auth.application.usecase.account.SignupUseCase;
import com.cjl.auth.application.usecase.account.UpdatePasswordUseCase;
import com.cjl.auth.infraestructure.security.JwtTokenUtil;

@Configuration
public class BeanConfiguration {
	
	@Bean
	public JavaMailSender getJavaMailSender() {
	    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	    mailSender.setHost("smtp.gmail.com");
	    mailSender.setPort(587);
	    
	    mailSender.setUsername("my.gmail@gmail.com");
	    mailSender.setPassword("password");
	    
	    Properties props = mailSender.getJavaMailProperties();
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.debug", "true");
	    
	    return mailSender;
	}

	@Bean
	public LoginUseCase getLoginUseCase(JwtTokenUtil jwtTokenUtil, AccountJpaRepositoryAdapter accountJpaRepositoryAdapter) {
		return new LoginUseCase(jwtTokenUtil, accountJpaRepositoryAdapter);
	}
	
	@Bean
	public SignupUseCase getSignupUseCase(AccountJpaRepositoryAdapter accountJpaRepositoryAdapter) {
		return new SignupUseCase(accountJpaRepositoryAdapter);
	}

	@Bean
	public PasswordRecoveryUseCase getPasswordRecoveryUseCase(JwtTokenUtil jwtTokenUtil, 
			AccountJpaRepositoryAdapter accountJpaRepositoryAdapter, EmailSenderAdapter emailSenderAdapter) {
		return new PasswordRecoveryUseCase(jwtTokenUtil, accountJpaRepositoryAdapter, emailSenderAdapter);
	}

	@Bean
	public UpdatePasswordUseCase getUpdatePasswordUseCase(AccountJpaRepositoryAdapter accountJpaRepositoryAdapter) {
		return new UpdatePasswordUseCase(accountJpaRepositoryAdapter);
	}
}
