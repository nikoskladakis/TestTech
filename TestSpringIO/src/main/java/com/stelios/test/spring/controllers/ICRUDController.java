package com.stelios.test.spring.controllers;

import java.util.List;

public interface ICRUDController<T, ID> {
	public T save( T ob );
	public T retrieve( ID id );
	public List<T> retrieveAll();
}
