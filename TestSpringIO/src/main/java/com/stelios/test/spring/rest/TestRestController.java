package com.stelios.test.spring.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stelios.test.obj.Author;
import com.stelios.test.spring.jpa.entities.AuthorEntity;
import com.stelios.test.spring.jpa.entities.BookEntity;
import com.stelios.test.spring.jpa.repos.AuthorRepository;
import com.stelios.test.spring.jpa.repos.BookRepository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RestController
public class TestRestController {
	@Autowired
	private AuthorRepository reposAuthor;
	@Autowired
	private BookRepository reposBook;
	
	
	@RequestMapping("/author/save")
	public AuthorEntity createAuthor( 
			@RequestParam(value="firstName") String firstName, 
			@RequestParam(value="lastName") String lastName ) {
		
		assertNonEmpty( firstName, "first name" );
		assertNonEmpty( lastName, "last name" );

		AuthorEntity ae = new AuthorEntity();
		ae.setFirstName( firstName );
		ae.setLastName( lastName );
		return reposAuthor.save( ae );
	}
	
	@RequestMapping("/book/save")
	public BookEntity createBook( 
			@RequestParam(value="title") String title, 
			@RequestParam(value="authorId") Long ... authorId ) {
		
		assertNonEmpty( title, "title" );
		assertThat( authorId.length, is( not( 0 ) ) );
		
		BookEntity book = new BookEntity();
		book.setTitle( title );
		List<AuthorEntity> authors = new ArrayList<AuthorEntity>();
		book.setAuthors( authors );

		for( Long aid : authorId ) {
			AuthorEntity ae = reposAuthor.findOne( aid );
			assertThat( ae, describedAs( "no author found with id " + aid, notNullValue()  ) );
			authors.add( ae );
		}
		return reposBook.save( book );
	}
	
	@RequestMapping("/author/retrieveAll")
	public List<Author> retrieveAuthors() {

		List<Author> as = new ArrayList<Author>();
		List<AuthorEntity> aes = reposAuthor.findAll();
		for( AuthorEntity ae : aes ) {
			Author a = new Author();
			as.add( a );
			
			a.setId( ae.getId() );
			a.setFirstName( ae.getFirstName() );
			a.setLastName( ae.getLastName() );
		}
		return as;
	}
	
	@RequestMapping("/book/retrieveAll")
	public List<BookEntity> retrieveBooks() {

		List<BookEntity> bes = reposBook.findAll();
		
		return bes;
	}
	
	@RequestMapping("/book/findByAuthorLastName")
	public List<BookEntity> retrieveBooks( @RequestParam(value="lastName") String lastName  ) {

		assertThat( lastName, describedAs( "non-empty last name", not( nullValue() ) ) );
		assertThat( lastName, describedAs( "non-empty last name", not( is( "" ) ) ) );
		
		List<BookEntity> bes = reposBook.findByAuthorLastName( lastName );
		
		return bes;
	}
	
	protected void assertNonEmpty( String ob, String description ) {
		assertThat( ob, describedAs( "non-empty " + description, not( nullValue() ) ) );
		assertThat( ob, describedAs( "non-empty " + description, not( is( "" ) ) ) );
		assertThat( ob, describedAs( "non-null " + description, not( is( "null" ) ) ) );
	}
}
