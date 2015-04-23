package com.stelios.test.spring.controllers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.describedAs;
import static org.hamcrest.Matchers.notNullValue;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.stelios.test.spring.jpa.entities.AuthorEntity;
import com.stelios.test.spring.jpa.entities.BookEntity;
import com.stelios.test.spring.jpa.repos.BookRepository;

@Controller
public class BookController extends BaseController<BookRepository, BookEntity, Long> {

	@Autowired
	private AuthorController authorController;
	
	@Override
	protected void saveAssertions( BookEntity book ) {
		super.saveAssertions( book );
		assertNonEmpty( book.getTitle(), "title" );
	}
	
	public BookEntity save( String title, List<Long> authorId ) {
		
		BookEntity book = new BookEntity();
		book.setTitle( title );
		List<AuthorEntity> authors = new ArrayList<AuthorEntity>();
		book.setAuthors( authors );

		for( Long aid : authorId ) {
			AuthorEntity ae = authorController.retrieve( aid );
			assertThat( ae, describedAs( "no author found with id " + aid, notNullValue()  ) );
			authors.add( ae );
		}
		
		return save( book );
	}

	public List<BookEntity> retrieveBooksByAuthorLastName( String authorLastName  ) {
		assertNonEmpty( authorLastName, "last name" );
		
		List<BookEntity> bes = repository.findByAuthorLastName( authorLastName );
		
		return bes;
	}
	
	
}
