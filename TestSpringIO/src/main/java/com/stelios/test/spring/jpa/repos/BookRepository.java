package com.stelios.test.spring.jpa.repos;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.stelios.test.spring.jpa.entities.BookEntity;

public interface BookRepository extends BaseRepository<BookEntity, Long> {
	@Query("select b from BookEntity b inner join b.authors a where lower(a.lastName) = lower(:lastName)")
	List<BookEntity> findByAuthorLastName( @Param("lastName") String lastName );
}
