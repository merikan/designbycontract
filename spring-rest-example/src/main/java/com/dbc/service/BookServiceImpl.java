package com.dbc.service;

import org.springframework.stereotype.Service;

import com.dbc.model.Book;

@Service
public class BookServiceImpl implements BookService{

	@Override
	public Book getBookByIsbn(String isbn) {
		Book book = new Book();
		book.setIsbn(isbn);
		return book;
	}

}
