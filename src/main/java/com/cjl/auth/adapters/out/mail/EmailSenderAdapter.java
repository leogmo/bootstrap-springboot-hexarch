package com.cjl.auth.adapters.out.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.cjl.auth.application.usecase.EmailSender;
import com.cjl.auth.domain.User;

@Component
public class EmailSenderAdapter implements EmailSender {
    
	@Autowired
	JavaMailSender emailSender;

    @Override
    public void sendRecoveryEmail(String token, User user, String url) {
    	SimpleMailMessage message = new SimpleMailMessage(); 
        message.setFrom("noreply@baeldung.com");
        message.setTo(user.getEmail()); 
        message.setSubject("Recuperar Senha"); 
        message.setText("Acesse para redefinir a senha: "+ url + "?token=" + token);
        emailSender.send(message);
    }
}
