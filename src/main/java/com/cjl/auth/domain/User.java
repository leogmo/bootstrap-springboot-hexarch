package com.cjl.auth.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cjl.auth.application.usecase.account.SignupDTO;

import lombok.*;

@ToString
@Getter
@EqualsAndHashCode(callSuper = false)
public class User {

	private static final int ENCRYPTION_STRENGTH = 12;
	
    private Long id;
    private String username;
    private String password;
    private String email;
    private List<Role> roles = new ArrayList<Role>();
    
    public User(SignupDTO dto) {
    	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(ENCRYPTION_STRENGTH);
    	this.username = dto.getUsername();
    	this.password = encoder.encode(dto.getPassword());
    	this.email = dto.getEmail();
    	this.roles.add(Role.ADMIN);
    }
    
    public User(Long id, String username, String password, String email, List<Role> roles) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.roles = roles;
	}

    public boolean matchesPassword(String aPassword){
    	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(ENCRYPTION_STRENGTH);
    	return encoder.matches(aPassword, this.password);
    }

	public void updatePassword(String aPassword) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(ENCRYPTION_STRENGTH);
		this.password = encoder.encode(aPassword);
	}
}
