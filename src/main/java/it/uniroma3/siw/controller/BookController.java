package it.uniroma3.siw.controller;

import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.model.Book;
import it.uniroma3.siw.model.Author;
import it.uniroma3.siw.repository.UserRepository;
import it.uniroma3.siw.service.BookService;
import jakarta.persistence.criteria.Path;
import jakarta.validation.Valid;
import it.uniroma3.siw.repository.AuthorRepository;
import it.uniroma3.siw.repository.BookRepository;

@Controller
public class BookController {

    private static final String UPLOAD_DIRECTORY = null;

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
                          @RequestParam("imageFile") MultipartFile imageFile,
                          Model model) {

        // Verifica se un file è stato caricato
        if (!imageFile.isEmpty()) {
            try {
                // Salva il file nella cartella uploads
                String uploadsDir = "uploads/";
                File uploadFile = new File(uploadsDir + imageFile.getOriginalFilename());
                imageFile.transferTo(uploadFile);
                
                // Imposta l'URL dell'immagine nel libro
                book.setUrlImage("/uploads/" + imageFile.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
                return "error"; // Puoi gestire eventuali errori
            }
        }

        // Salva il libro
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

    @PostMapping("/admin/formUpdateBook/{id}")
    public String editingBook(@PathVariable("id") Long id,
                              @Valid @ModelAttribute("book") Book book,
                              BindingResult bindingResult,
                              @RequestParam(value = "authorNames[]", required = false) String[] authorNames,
                              @RequestParam(value = "authorSurnames[]", required = false) String[] authorSurnames,
                              @RequestParam(value = "imageFile", required = false) MultipartFile imageFile,
                              Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("book", this.bookService.findById(id));
            return "admin/formUpdateBook.html";
        }

        Book originalBook = this.bookService.findById(id);
        originalBook.setTitle(book.getTitle());
        originalBook.setYear(book.getYear());

        // Gestisci l'immagine
        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                // Definisci la directory di upload
                String uploadDir = "C:/myapp/uploads/"; // Usa il percorso corretto
                File uploadDirFile = new File(uploadDir);

                if (!uploadDirFile.exists()) {
                    uploadDirFile.mkdirs(); // Crea la directory se non esiste
                }

                // Crea il file da salvare
                String fileName = imageFile.getOriginalFilename();
                File fileToSave = new File(uploadDir + fileName);
                imageFile.transferTo(fileToSave);

                // Imposta l'URL dell'immagine
                originalBook.setUrlImage("/uploads/" + fileName);
            } catch (IOException e) {
                e.printStackTrace();
                model.addAttribute("messaggioErrore", "Errore nel salvataggio dell'immagine");
                model.addAttribute("book", originalBook);
                return "admin/formUpdateBook.html";
            }
        } else {
            // Se non viene caricato un nuovo file, mantieni l'URL esistente
            originalBook.setUrlImage(book.getUrlImage());
        }

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
