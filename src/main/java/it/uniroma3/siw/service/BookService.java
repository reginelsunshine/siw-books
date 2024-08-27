package it.uniroma3.siw.service;

import org.springframework.stereotype.Service;

import java.util.List;

import jakarta.transaction.Transactional;

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

	public Book save(Book book) {
		return this.bookRepository.save(book);
	}

	public List<Book> findByYear(Integer year) {
		// TODO Auto-generated method stub
		return this.bookRepository.findByYear(year);
	}

    
}

