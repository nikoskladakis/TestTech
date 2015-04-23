package com.stelios.test.spring.controllers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.describedAs;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.NoRepositoryBean;

import com.stelios.test.spring.jpa.repos.BaseRepository;

@NoRepositoryBean
public class BaseController<R extends BaseRepository<T, ID>, T, ID extends Serializable> implements ICRUDController<T, ID> {
	
	@Autowired
	protected R repository;

	@Override
	public T save(T ob) {
		saveAssertions( ob );
		return repository.save( ob );
	}

	@Override
	public T retrieve(ID id) {
		retrieveAssertions( id );
		return repository.findOne( id );
	}

	@Override
	public List<T> retrieveAll() {
		return repository.findAll();
	}
	
	protected void saveAssertions( T ob ) {
		assertNonNull( ob, "object" );
	}
	
	private void retrieveAssertions( ID id ) {
		assertNonNull( id, "object id" );
	}
	
	protected void assertNonEmpty( String ob, String description ) {
		assertNonNull( ob, description );
		assertThat( ob, describedAs( "non-empty " + description, not( is( "" ) ) ) );
		assertThat( ob, describedAs( "non-'null' " + description, not( is( "null" ) ) ) );
	}
	
	protected void assertNonNull( Object ob, String description ) {
		assertThat( ob, describedAs( "non-null " + description, not( nullValue() ) ) );
	}

}
