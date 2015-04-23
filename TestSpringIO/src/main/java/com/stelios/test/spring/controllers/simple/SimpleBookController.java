package com.stelios.test.spring.controllers.simple;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.describedAs;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.is;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.stelios.test.spring.jpa.entities.AuthorEntity;
import com.stelios.test.spring.jpa.entities.BookEntity;
import com.stelios.test.spring.jpa.repos.BookRepository;

@Controller
public class SimpleBookController extends SimpleBaseController< BookEntity, Long> {

	@Autowired
	private SimpleAuthorController authorController;
	
	@Autowired
	private BookRepository reposBook;
	
	@Override
	public BookEntity save(BookEntity book) {
		assertNonNull( book, "book" );
		assertNonEmpty( book.getTitle(), "first name" );
		List<AuthorEntity> authors = book.getAuthors();
		assertThat( authors.size(), is( not( 0 ) ) );
		
		return reposBook.save( book );
	}
	
	
	@Override
	public BookEntity retrieve(Long id) {
		assertNonNull( id, "id" );
		
		return reposBook.findOne( id );
	}

	@Override
	public List<BookEntity> retrieveAll() {
		
		return reposBook.findAll();
	}
	
	public List<BookEntity> retrieveBooksByAuthorLastName( String authorLastName  ) {
		assertNonEmpty( authorLastName, "last name" );
		
		List<BookEntity> bes = reposBook.findByAuthorLastName( authorLastName );
		
		return bes;
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

	
}
