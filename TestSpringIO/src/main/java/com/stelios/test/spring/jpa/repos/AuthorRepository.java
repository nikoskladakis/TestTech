package com.stelios.test.spring.jpa.repos;

import org.springframework.stereotype.Repository;

import com.stelios.test.spring.jpa.entities.AuthorEntity;

public interface AuthorRepository extends BaseRepository<AuthorEntity, Long> {

}
