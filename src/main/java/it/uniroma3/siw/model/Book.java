package it.uniroma3.siw.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Book {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String title;
	private Integer year;
	
	@ManyToMany(mappedBy = "books")
	private Set<Author> authors;

	// Costruttore predefinito
	public Book() {
		this.authors = new HashSet<>();
	}
	
	// Costruttore parametrizzato
	public Book(String title, Integer year) {
		this.title = title;
		this.year = year;
		this.authors = new HashSet<>();
	}
	
	// Getters e Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Set<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(Set<Author> authors) {
		this.authors = authors;
	}
	
	 @Override
     public int hashCode() {
         return Objects.hash(title, year);
     }
 
     @Override
     public boolean equals(Object obj) {
         if (this == obj)
             return true;
         if (obj == null)
             return false;
         if (getClass() != obj.getClass())
             return false;
         Book other = (Book) obj;
         return Objects.equals(title, other.title) && year.equals(other.year);
     }
	
}
