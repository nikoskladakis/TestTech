package com.stelios.test.spring.jpa.entities;

import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "book")
public class BookEntity  {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	protected Long id;
	
	@Column(name = "title")
	protected String title;
	
	@ManyToMany(cascade={CascadeType.MERGE})
	@JoinTable(name="book_author", joinColumns={@JoinColumn(name="book_id")}, 
		inverseJoinColumns={@JoinColumn(name="author_id")})
	protected List<AuthorEntity> authors;
	
	public BookEntity() {
		
	}
	
	public BookEntity(String title, AuthorEntity... authors) {
		super();
		this.title = title;
		this.authors = Arrays.asList( authors );
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<AuthorEntity> getAuthors() {
		return authors;
	}
	public void setAuthors(List<AuthorEntity> authors) {
		this.authors = authors;
	}
	
	
}
