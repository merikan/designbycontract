package com.dbc.service;

import com.dbc.model.Book;

public interface BookService {
	public Book getBookByIsbn(String isbn);

	public Book getBookByTitle(String title);
}
