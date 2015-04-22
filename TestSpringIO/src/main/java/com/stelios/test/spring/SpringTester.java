package com.stelios.test.spring;

import org.springframework.boot.CommandLineRunner;

/*
@SpringBootApplication
@ComponentScan( basePackages = {
		 "com.stelios.test.spring"
})
//*/
public class SpringTester implements CommandLineRunner {
	public void run(String... args) throws Exception {
		
	}
	/*
	private static Logger log = Logger.getLogger( SpringTester.class.getName() );
	
	@Autowired
	private AuthorRepository reposAuthor;
	@Autowired
	private BookRepository reposBook;
	
	public static void main(String[] args) {
        SpringApplication.run(SpringTester.class);
    }
	

	public void run(String... args) throws Exception {
		log.entering( "SpringTester", "run" );
		AuthorEntity a1 = new AuthorEntity( "nikolaos", "kladakis");
		AuthorEntity a2 = new AuthorEntity( "stelios", "psarras");
		
		List<BookEntity> bs = new ArrayList<BookEntity>();
		bs.add( new BookEntity( "book1", a1 ) );
		bs.add( new BookEntity( "book2", a2 ) );
		bs.add( new BookEntity( "book3", a1, a2 ) );
		
		// save authors first and recover their ids into the objects...
		a1.setId( reposAuthor.save( a1 ).getId() );
		a2.setId( reposAuthor.save( a2 ).getId() );
		
		for( BookEntity b : bs ) {
			b = reposBook.save( b );
			log.info( b.getTitle() + " got id: " + b.getId() );
		}
		
		log.exiting( "SpringTester", "run" );
	}
	
	//*/
}
