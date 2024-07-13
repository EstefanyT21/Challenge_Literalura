package Challenge_Libros.respository;

import Challenge_Libros.model.Autor;
import Challenge_Libros.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LibroRepository extends JpaRepository <Libro,Long> {
    //@Query("SELECT e FROM Serie s JOIN s.episodios e WHERE e.titulo ILIKE %:nombreEpisodio%")    //(p. 26)
    //List<Episodio> episodioPorNombre(String nombreEpisodio);

    @Query("SELECT a FROM Libro l JOIN l.autor a")
    List<Autor> todosLosAutores();
}