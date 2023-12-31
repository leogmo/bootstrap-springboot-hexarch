package com.cjl.auth.adapters.out.jpa.account;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


import com.cjl.auth.domain.Role;
import com.cjl.auth.domain.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "users")
@Table(name = "users")
public class UserJpa {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;
    private Long empresaId;
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<RoleJpa> roles = new ArrayList<>();

    public User toUser(){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setId(id);
        user.setRoles(getRoles().stream().map(RoleJpa::getName).map(Role::valueOf).collect(Collectors.toList()));
        return user;
    }
}
