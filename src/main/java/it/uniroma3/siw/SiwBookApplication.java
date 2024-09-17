package it.uniroma3.siw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.uniroma3.siw.repository.AuthorRepository;

@SpringBootApplication
public class SiwBookApplication /*implements CommandLineRunner*/ { /*ho bisogno di CommandLineRunner per eseguire del codice subito dopo
                                                               l'avvio dell'app*/
    @Autowired
    private AuthorRepository authorRepository;

    public static void main(String[] args) {
        SpringApplication.run(SiwBookApplication.class, args);
    }

  /*  // Metodo che viene eseguito dopo l'avvio dell'applicazione
    @Override
    public void run(String... args) throws Exception {
        long count = authorRepository.count();
        System.out.println("Il numero di occorrenze dell'entità autore è di: " + count);
    } */
}
