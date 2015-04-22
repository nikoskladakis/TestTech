package com.stelios.test.obj;

import java.util.List;

public class Book extends STObject {
	protected String title;
	protected List<Author> authors;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<Author> getAuthors() {
		return authors;
	}
	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}
	
	
}
