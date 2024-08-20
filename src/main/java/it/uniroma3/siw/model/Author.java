package it.uniroma3.siw.model;

import java.time.LocalDate;
import java.util.HashSet;

import java.util.Objects;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;


@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	private String name;
	private String surname;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateOfBirth;
	private String urlOfPicture;
	
	/*ASSOCIAZIONI ANCORA DA FARE*/
	
	@ManyToMany(mappedBy="authors")
	private Set<Book> books=new HashSet<>();
	
	public Author(){
		this.books=new HashSet<>();
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public String getUrlOfPicture() {
		return urlOfPicture;
	}
	
	public void setUrlOfPicture(String urlOfPicture) {
		this.urlOfPicture = urlOfPicture;
	}
	
	public Set<Book> getBooks(){
		return books;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(name, surname);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Author other = (Author) obj;
		return Objects.equals(name, other.name) && Objects.equals(surname, other.surname);
	}
}