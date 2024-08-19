package it.uniroma3.siw.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.model.Author;
import it.uniroma3.siw.repository.AuthorRepository;

@Controller
public class AuthorController {
	
	@Autowired 
	private AuthorRepository authorRepository;

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
	public String newAuthor(@ModelAttribute("author") Author author, Model model) {
		if (!authorRepository.existsByNameAndSurname(author.getName(), author.getSurname())) {
			this.authorRepository.save(author); 
			model.addAttribute("author", author);
			return "author.html";
		} else {
			model.addAttribute("messaggioErrore", "Questo autore esiste già");
			return "admin/formNewAuthor.html"; 
		}
	}

	@GetMapping("/author/{id}")
	public String getAuthor(@PathVariable("id") Long id, Model model) {
		model.addAttribute("author", this.authorRepository.findById(id).get());
		return "author.html";
	}

	@GetMapping("/author")
	public String getAuthor(Model model) {
		model.addAttribute("author", this.authorRepository.findAll());
		return "author.html";
	}
}
