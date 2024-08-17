package it.uniroma3.siw.repository;

import org.springframework.data.repository.CrudRepository;
import it.uniroma3.siw.model.Book;
import java.util.List;

public interface BookRepository extends CrudRepository<Book,Long> {
	
	public List<Book>findByYear(Integer year);
	
	public boolean existsByTitleAndYear(String title, Integer year);
	
}
