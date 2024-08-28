package it.uniroma3.siw.repository;

import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


import it.uniroma3.siw.model.Author;

public interface AuthorRepository extends CrudRepository<Author, Long> {
	public boolean existsByNameAndSurname(String name, String surname);	
	
	


/*query...*/
	

    // Trova un autore per nome e cognome
    @Query("SELECT a FROM Author a WHERE a.name = :name AND a.surname = :surname")
    Author findByNameAndSurname(@Param("name") String name, @Param("surname") String surname);

    // Trova tutti gli autori con un determinato nome
    @Query("SELECT a FROM Author a WHERE a.name = :name")
    List<Author> findByName(@Param("name") String name);

    // Trova tutti gli autori nati dopo una data specifica
    @Query("SELECT a FROM Author a WHERE a.dateOfBirth > :date")
    List<Author> findAuthorsBornAfter(@Param("date") LocalDate date);
}
