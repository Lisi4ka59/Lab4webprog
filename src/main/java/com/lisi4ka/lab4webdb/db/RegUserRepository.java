package com.lisi4ka.lab4webdb.db;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RegUserRepository extends CrudRepository<RegUser, Long> {
    Optional<RegUser> findByUsername(String username);
}
