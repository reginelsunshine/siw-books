package it.uniroma3.siw.controller;

import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

//import it.uniroma3.siw.controller.validator.BookValidator;
import it.uniroma3.siw.model.Book;
import it.uniroma3.siw.model.Author;
import it.uniroma3.siw.repository.UserRepository;
import it.uniroma3.siw.service.BookService;
import jakarta.validation.Valid;
import it.uniroma3.siw.repository.AuthorRepository;
import it.uniroma3.siw.repository.BookRepository;


@Controller
public class BookController {
	@Autowired BookService bookService;
	
	@Autowired 
	private BookRepository bookRepository;
	
	@Autowired UserRepository userRepository;
	@Autowired 
	private AuthorRepository authorRepository;
	/*@Autowired 
	private BookValidator bookValidator;*/
	
	@GetMapping("/books")
    public String home(Model model) {
		model.addAttribute("users", userRepository.findAll());
        return "index"; // Ritorna il nome della vista "index.html" 
    }
	
	@GetMapping("/book/{id}")
	public String getBook(@PathVariable("id") Long id, Model model) {
	    Book book = this.bookService.findById(id);
	    model.addAttribute("book", book);

	    // Non è necessario passare la lista di tutti gli autori
	    // Utilizza gli autori associati al libro
	    model.addAttribute("authors", book.getAuthors());

	    return "book.html";
	}
	
	@GetMapping("/book")
	public String showBooks(Model model) {
		model.addAttribute("books", this.bookService.findAll());
		return "books.html";
	}
	
	@GetMapping("/admin/formNewBook")
	public String formNewBook(Model model) {
		model.addAttribute("book", new Book());
		return "admin/formNewBook.html";
	}
	
	@PostMapping("/book")
	public String newBook(@ModelAttribute("book") Book book, Model model) {
		this.bookService.save(book);
		model.addAttribute("book", book);
		return "redirect:book/"+book.getId();
	}
	
	@GetMapping("/formSearchBooks")
	public String formSearchBooks() {
		return "formSearchBooks.html";
	}
	
	@GetMapping("/foundBooks")                       //metodo per la gestione del redirect per evitare"conferma reinvio modulo cache miss
	public String showFoundBooks(@RequestParam Integer year, Model model) {
	    model.addAttribute("books", this.bookService.findByYear(year));
	    return "foundBooks.html";
	}
	
	
	@PostMapping("/searchBooks")
	public String searchBooks(Model model, @RequestParam Integer year) {
		model.addAttribute("books", this.bookService.findByYear(year));
		 return "redirect:/foundBooks?year=" + year;                        //redirect per evitare cache miss
	}
	
	@GetMapping(value="/admin/indexBook")
	public String indexBook() {
		return "admin/indexBook.html";
	}
	
	@GetMapping(value="/admin/manageBooks")
	public String manageBooks(Model model) {
		model.addAttribute("books", this.bookRepository.findAll());
		return "admin/manageBooks.html";
	}
	

	
	
	

}
