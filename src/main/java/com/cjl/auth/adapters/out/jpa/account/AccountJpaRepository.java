package com.cjl.auth.adapters.out.jpa.account;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountJpaRepository extends JpaRepository<UserJpa, Long> {
    Optional<UserJpa> findByEmail(String email);
}
