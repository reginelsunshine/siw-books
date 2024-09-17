package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.model.Author;
import it.uniroma3.siw.service.AuthorService;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class AuthorController {

    @Autowired 
    private AuthorService authorService;

    @GetMapping(value="/admin/formNewAuthor")
    public String formNewAuthor(Model model) {
        model.addAttribute("author", new Author());
        return "admin/formNewAuthor.html";
    }

    @GetMapping(value="/admin/indexAuthor")
    public String indexAuthor() {
        return "admin/indexAuthor.html";
    }
    
    @PostMapping("/admin/author")
    public String newAuthor(@ModelAttribute("author") Author author,
                            @RequestParam("file") MultipartFile file,
                            Model model) {
        if (!authorService.authorExists(author.getName(), author.getSurname())) {
            if (!file.isEmpty()) {
                String fileName = file.getOriginalFilename();
                String uploadDir = "C:/myapp/uploads/"; // Usa il percorso corretto
                File uploadDirFile = new File(uploadDir);
                
                if (!uploadDirFile.exists()) {
                    uploadDirFile.mkdirs(); // Crea la directory se non esiste
                }
                
                File fileToSave = new File(uploadDir + fileName);
                try {
                    file.transferTo(fileToSave);
                    author.setUrlOfPicture("/uploads/" + fileName);
                } catch (IOException e) {
                    e.printStackTrace();
                    model.addAttribute("messaggioErrore", "Errore nel salvataggio dell'immagine");
                    return "admin/formNewAuthor.html";
                }
            }
            authorService.saveAuthor(author);
            model.addAttribute("author", author);
            return "author.html";
        } else {
            model.addAttribute("messaggioErrore", "Questo autore esiste già");
            return "admin/formNewAuthor.html";
        }
    }
    
    @GetMapping("/author/{id}")
    public String getAuthor(@PathVariable("id") Long id, Model model) {
        model.addAttribute("author", authorService.getAuthorById(id));
        return "author.html";
    }

    @GetMapping("/author")
    public String getAuthors(Model model) {
        model.addAttribute("authors", authorService.getAllAuthors());
        return "authors.html";
    }

    @GetMapping("/api/authors/search")
    public ResponseEntity<List<Author>> searchAuthors(@RequestParam("keyword") String keyword) {
        List<Author> authors = authorService.searchAuthors(keyword);
        return ResponseEntity.ok(authors);
    }
}
