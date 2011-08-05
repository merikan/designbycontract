package com.dbc.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dbc.model.Book;
import com.dbc.service.BookService;

@Controller
public class BookController {
 
	@Autowired
	BookService bookService;
 
	@RequestMapping(value = "/books/{isbn}")
	public ModelAndView getBookByIsbn(@PathVariable String isbn) {
		Book book = bookService.getBookByIsbn(isbn);
		ModelAndView mav = new ModelAndView("bookXmlView", BindingResult.MODEL_KEY_PREFIX + "book", book);
		return mav;
	}
	
	@RequestMapping(value = "/books/{title}")
	public ModelAndView getBookByTitle(@PathVariable String title) {
		Book book = bookService.getBookByTitle(title);
		ModelAndView mav = new ModelAndView("bookXmlView", BindingResult.MODEL_KEY_PREFIX + "book", book);
		return mav;
	}
}