package com.stelios.test.spring.controllers.simple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.stelios.test.spring.jpa.entities.AuthorEntity;
import com.stelios.test.spring.jpa.repos.AuthorRepository;

@Controller
public class SimpleAuthorController extends SimpleBaseController<AuthorEntity, Long> {
	

	@Autowired
	private AuthorRepository reposAuthor;
	
	@Override
	public AuthorEntity save( AuthorEntity ae ) {
		assertNonNull( ae, "author" );
		assertNonEmpty( ae.getFirstName(), "first name" );
		assertNonEmpty( ae.getLastName(), "last name" );

	
		return reposAuthor.save( ae );
	}
	
	@Override
	public AuthorEntity retrieve( Long id ) {
		assertNonNull( id, "id" );

		return reposAuthor.findOne( id );
	}
	
	@Override
	public List<AuthorEntity> retrieveAll() {
		return reposAuthor.findAll();
	}

}
