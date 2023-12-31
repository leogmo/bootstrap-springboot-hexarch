package com.cjl.auth.infrastructure.security.jwt;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cjl.auth.adapters.out.jpa.AccountJpaRepositoryAdapter;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	AccountJpaRepositoryAdapter accountJpaRepositoryAdapter;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<com.cjl.auth.domain.User> user = accountJpaRepositoryAdapter.findByEmail(username);
		if (user.isEmpty()) {
			throw new UsernameNotFoundException("Usuário não encontrado!");
		}
		return new User(user.get().getEmail(), user.get().getPassword(),
					new ArrayList<>());
		
	}
}