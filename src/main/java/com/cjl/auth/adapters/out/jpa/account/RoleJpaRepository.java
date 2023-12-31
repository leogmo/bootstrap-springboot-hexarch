package com.cjl.auth.adapters.out.jpa.account;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleJpaRepository  extends JpaRepository<RoleJpa, Long> {
    Optional<RoleJpa> findByName(String name);
}
