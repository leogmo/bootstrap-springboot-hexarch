package com.cjl.auth.adapters.out.jpa;


import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.cjl.auth.adapters.out.jpa.account.AccountJpaRepository;
import com.cjl.auth.adapters.out.jpa.account.RoleJpa;
import com.cjl.auth.adapters.out.jpa.account.RoleJpaRepository;
import com.cjl.auth.adapters.out.jpa.account.UserJpa;
import com.cjl.auth.application.usecase.AccountRepository;
import com.cjl.auth.application.usecase.account.SignupDTO;
import com.cjl.auth.domain.Role;
import com.cjl.auth.domain.User;

@Component
public class AccountJpaRepositoryAdapter implements AccountRepository {
	
    private AccountJpaRepository accountJpaRepository;
    private RoleJpaRepository roleJpaRepository;
    
    public AccountJpaRepositoryAdapter(AccountJpaRepository accountJpaRepository,
    		RoleJpaRepository roleJpaRepository) {
    	this.accountJpaRepository = accountJpaRepository;
    	this.roleJpaRepository = roleJpaRepository;
    }
    
    @Override
    public Optional<User> findByEmail(String email) {
        return accountJpaRepository.findByEmail(email).map(UserJpa::toUser);
    }

    @Override
    public User signup(SignupDTO signupDTO) throws Exception {
    	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
    	
        UserJpa accountJpa = new UserJpa();
        accountJpa.setEmail(signupDTO.getEmail());
        accountJpa.setPassword(encoder.encode(signupDTO.getPassword()));
        accountJpa.setUsername(signupDTO.getUsername());
        accountJpa.setEmpresaId(1L);
        accountJpaRepository.save(accountJpa);
        accountJpa.getRoles().add(getAdminRole());
        return accountJpaRepository.save(accountJpa).toUser();
    }

    @Override
    public void save(User user) throws Exception {
        UserJpa account = accountJpaRepository.findById(user.getId()).orElseThrow(
                () -> new Exception("Não foi possível encontrar o user")
        );
        account.setPassword(user.getPassword());
        accountJpaRepository.save(account);
    }

    private RoleJpa getAdminRole() throws Exception {
        return roleJpaRepository.findByName(Role.ADMIN.toString()).orElseThrow(
                () -> new Exception("Não foi possível encontrar a Role")
        );
    }
}
