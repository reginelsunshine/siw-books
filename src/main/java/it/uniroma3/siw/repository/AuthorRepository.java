package it.uniroma3.siw.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siw.model.Author;

import java.util.List;

public interface AuthorRepository extends CrudRepository<Author, Long> {
    boolean existsByNameAndSurname(String name, String surname);

    @Query("SELECT a FROM Author a WHERE a.name = :name AND a.surname = :surname")
    Author findByNameAndSurname(@Param("name") String name, @Param("surname") String surname);

    @Query("SELECT a FROM Author a WHERE LOWER(a.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(a.surname) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Author> searchByNameOrSurnameContaining(@Param("keyword") String keyword);
    
    @Query("SELECT a FROM Author a WHERE " +
            "LOWER(a.name) LIKE LOWER(CONCAT('%', :searchText, '%')) OR " +
            "LOWER(a.surname) LIKE LOWER(CONCAT('%', :searchText, '%'))")
    List<Author> searchByNameOrSurnameOrBiography(@Param("searchText") String searchText);
}
