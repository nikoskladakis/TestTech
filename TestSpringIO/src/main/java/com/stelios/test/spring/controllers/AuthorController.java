package com.stelios.test.spring.controllers;

import org.springframework.stereotype.Service;

import com.stelios.test.spring.jpa.entities.AuthorEntity;
import com.stelios.test.spring.jpa.repos.AuthorRepository;

@Service
public class AuthorController extends BaseController<AuthorRepository, AuthorEntity, Long> {
	
	protected void saveAssertions( AuthorEntity ae ) {
		super.saveAssertions( ae );
		assertNonEmpty( ae.getFirstName(), "first name" );
		assertNonEmpty( ae.getLastName(), "last name" );
	}
}
