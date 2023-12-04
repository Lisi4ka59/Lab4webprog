package com.lisi4ka.lab4webdb.db;

import org.springframework.data.repository.CrudRepository;


public interface DotRepository extends CrudRepository<Dot, Long> {
    Iterable<Dot> findAllByUserId(long userId);
    //void deleteDotByUserId(long userId);
}
