package com.uuhere.reactivecrud;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends R2dbcRepository<Animal, Long> {
}
