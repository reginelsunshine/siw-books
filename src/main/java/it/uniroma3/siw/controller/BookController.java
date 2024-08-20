package it.uniroma3.siw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.model.Book;
import it.uniroma3.siw.repository.UserRepository;
import it.uniroma3.siw.service.BookService;

@Controller
public class BookController {
	@Autowired BookService bookService;
	
	@Autowired UserRepository userRepository;
	
	@GetMapping("/books")
    public String home(Model model) {
		model.addAttribute("users", userRepository.findAll());
        return "index"; // Ritorna il nome della vista "index.html" 
    }
	
	@GetMapping("/book/{id}")
	public String getBook(@PathVariable("id") Long id, Model model) {
		model.addAttribute("book",this.bookService.findById(id));
		return "book.html";
	}
	
	@GetMapping("/book")
	public String showBooks(Model model) {
		model.addAttribute("books", this.bookService.findAll());
		return "books.html";
	}
	
	@GetMapping("/formNewBook")
	public String formNewBook(Model model) {
		model.addAttribute("book", new Book());
		return "formNewBook.html";
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
	
	@PostMapping("/searchBooks")
	public String searchBooks(Model model, @RequestParam Integer year) {
		model.addAttribute("books", this.bookService.findByYear(year));
		return "foundBooks.html";
	}
}
