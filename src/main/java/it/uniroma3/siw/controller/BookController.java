package it.uniroma3.siw.controller;

import org.springframework.stereotype.Controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.model.Book;
import it.uniroma3.siw.model.Author;
import it.uniroma3.siw.repository.UserRepository;
import it.uniroma3.siw.service.BookService;
import jakarta.validation.Valid;
import it.uniroma3.siw.repository.AuthorRepository;
import it.uniroma3.siw.repository.BookRepository;

@Controller
public class BookController {

    @Autowired 
    private BookService bookService;
	
    @Autowired 
    private BookRepository bookRepository;
	
    @Autowired 
    private UserRepository userRepository;
	
    @Autowired 
    private AuthorRepository authorRepository;
	
    @GetMapping("/books")
    public String home(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "index"; // Ritorna il nome della vista "index.html" 
    }
	
    @GetMapping("/book/{id}")
    public String getBook(@PathVariable("id") Long id, Model model) {
        Book book = this.bookService.findById(id);
        model.addAttribute("book", book);

        // Aggiungi solo gli autori associati al libro
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
        model.addAttribute("authors", authorRepository.findAll());  // Passa tutti gli autori al modello
        return "admin/formNewBook.html";
    }


    @PostMapping("/book")
    public String newBook(@ModelAttribute("book") Book book,
                          @RequestParam(value = "authors", required = false) Set<Long> authorIds,
                          Model model) {
        // Gestisci gli autori selezionati dalle checkbox
        if (authorIds != null) {
            Set<Author> selectedAuthors = new HashSet<>();
            for (Long authorId : authorIds) {
                Author author = authorRepository.findById(authorId).orElse(null);
                if (author != null) {
                    selectedAuthors.add(author);
                }
            }
            book.setAuthors(selectedAuthors);
        }

        // Salva il libro con gli autori selezionati
        bookService.save(book);
        model.addAttribute("book", book);
        return "redirect:/book/" + book.getId();
    }



	
    @GetMapping("/formSearchBooks")
    public String formSearchBooks() {
        return "formSearchBooks.html";
    }
	
    @GetMapping("/foundBooks")
    public String showFoundBooks(@RequestParam Integer year, Model model) {
        model.addAttribute("books", this.bookService.findByYear(year));
        return "foundBooks.html";
    }
	
    @PostMapping("/searchBooks")
    public String searchBooks(Model model, @RequestParam Integer year) {
        model.addAttribute("books", this.bookService.findByYear(year));
        return "redirect:/foundBooks?year=" + year;
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
	
    @GetMapping(value = "/admin/formUpdateBook/{id}")
    public String editBook(@PathVariable("id") Long id, Model model) {
        model.addAttribute("book", this.bookService.findById(id));
        return "admin/formUpdateBook.html";
    }

    @PostMapping(value = "/admin/formUpdateBook/{id}")
    public String editingBook(@PathVariable("id") Long id, 
                              @Valid @ModelAttribute("book") Book book,
                              BindingResult bindingResult, 
                              @RequestParam(value = "authorNames[]", required = false) String[] authorNames,
                              @RequestParam(value = "authorSurnames[]", required = false) String[] authorSurnames,
                              Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("book", this.bookService.findById(id));
            return "admin/formUpdateBook.html";
        }

        Book originalBook = this.bookService.findById(id);
        originalBook.setTitle(book.getTitle());
        originalBook.setYear(book.getYear());
        originalBook.setUrlImage(book.getUrlImage());

        // Gestisci gli autori
        Set<Author> authors = new HashSet<>();
        if (authorNames != null && authorSurnames != null) {
            for (int i = 0; i < authorNames.length; i++) {
                String name = authorNames[i];
                String surname = authorSurnames[i];
                if (name != null && !name.trim().isEmpty() && surname != null && !surname.trim().isEmpty()) {
                    Author author = this.authorRepository.findByNameAndSurname(name, surname);
                    if (author == null) {
                        author = new Author();
                        author.setName(name);
                        author.setSurname(surname);
                        this.authorRepository.save(author);
                    }
                    authors.add(author);
                }
            }
        }
        originalBook.setAuthors(authors);

        this.bookService.save(originalBook);

        model.addAttribute("book", originalBook);
        return "redirect:/book/" + id;
    }
}
