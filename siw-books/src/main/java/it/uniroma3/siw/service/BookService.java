package it.uniroma3.siw.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import it.uniroma3.siw.model.Book;
import it.uniroma3.siw.repository.BookRepository;

@Service
public class BookService {
	@Autowired
	private BookRepository bookRepository;
	
	public Book findById(Long id) {
		return bookRepository.findById(id).get();
	}
	
	public Iterable<Book>findAll(){
		return bookRepository.findAll();
	}
}

