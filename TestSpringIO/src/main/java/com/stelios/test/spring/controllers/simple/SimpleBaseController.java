package com.stelios.test.spring.controllers.simple;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.describedAs;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

import org.springframework.data.repository.NoRepositoryBean;

import com.stelios.test.spring.controllers.ICRUDController;

@NoRepositoryBean
public abstract class SimpleBaseController<T, ID> implements ICRUDController<T, ID> {
	
	protected void assertNonEmpty( String ob, String description ) {
		assertNonNull( ob, description );
		assertThat( ob, describedAs( "non-empty " + description, not( is( "" ) ) ) );
		assertThat( ob, describedAs( "non-'null' " + description, not( is( "null" ) ) ) );
	}
	
	protected void assertNonNull( Object ob, String description ) {
		assertThat( ob, describedAs( "non-null " + description, not( nullValue() ) ) );
	}

}
