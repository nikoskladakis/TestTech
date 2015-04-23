package com.stelios.test.junit;

import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stelios.test.spring.ApplicationContext;
import com.stelios.test.spring.controllers.AuthorController;
import com.stelios.test.spring.controllers.BookController;
import com.stelios.test.spring.jpa.entities.AuthorEntity;
import com.stelios.test.spring.jpa.entities.BookEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationContext.class})
public class BookControllerTester {
	

	@Autowired
	private BookController bookController;
	@Autowired
	private AuthorController authorController;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	private Long nk_id,sp_id;
	
	private boolean hasSetUp = false;
	
	@Before
	public void setUp() throws Exception {
		if( !hasSetUp ) {
			AuthorEntity nk = new AuthorEntity( "nikos", "kladakis");
			nk = authorController.save( nk );
			nk_id = nk.getId();
			
			BookEntity bk1 = bookController.save( "istoria mou", Arrays.asList( new Long[]{ nk_id } ) );
			
			AuthorEntity sp = new AuthorEntity( "stelios", "psarras");
			sp = authorController.save( sp );
			sp_id = sp.getId();
			
			BookEntity bk2 = bookController.save( "my story", Arrays.asList( new Long[]{ sp_id } ) );
			
			hasSetUp = true;
		}
	}

	@After
	public void tearDown() throws Exception {
	}

	public void readAllBooks() {
		
		
		List<BookEntity> books = bookController.retrieveAll();
		System.out.println( "*******************" );
		System.out.println( "*******************" );
		for( BookEntity book : books ) { 
			System.out.println( book.getId() + " " + book.getTitle() );
		}
		System.out.println( "*******************" );
		System.out.println( "*******************" );
		
	}
	
	@Test
	public void retrieveBooksByNikos() {
		assertThat( "Wrong number of books retrieved", retrieveBooksBy( "kladakis" ).size() == 1 );
	}
	@Test
	public void retrieveBooksByStelios() {
		assertThat( "Wrong number of books retrieved", retrieveBooksBy( "psarras" ).size() == 1 );
	}
	@Test
	public void retrieveBooksByUnknownAuthor() {
		assertThat( "Wrong number of books retrieved", retrieveBooksBy( "other" ).size() == 0 );
	}
	
	@Test
	public void retrieveBooksByEmptyAuthor() {
		assertThat( "Wrong number of books retrieved", retrieveBooksBy( "" ).size() == 0 );
	}
	
	public List<BookEntity> retrieveBooksBy( String authName ) {
		return bookController.retrieveBooksByAuthorLastName( authName );
	}

}
