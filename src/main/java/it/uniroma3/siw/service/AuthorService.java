package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.uniroma3.siw.model.Author;
import it.uniroma3.siw.repository.AuthorRepository;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    // Salva un autore nel repository
    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    // Trova un autore per ID
    public Author getAuthorById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    // Trova tutti gli autori
    public List<Author> getAllAuthors() {
        return (List<Author>) authorRepository.findAll();
    }

    // Ricerca autori basata su un keyword
    public List<Author> searchAuthors(String keyword) {
        return authorRepository.searchByNameOrSurnameContaining(keyword);
    }

    // Controlla se un autore esiste già basato su nome e cognome
    public boolean authorExists(String name, String surname) {
        return authorRepository.existsByNameAndSurname(name, surname);
    }
    
   /* public long countAuthors() {
        return authorRepository.count();
    } */
}
