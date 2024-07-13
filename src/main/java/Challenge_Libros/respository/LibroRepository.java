package Challenge_Libros.respository;

import Challenge_Libros.model.Autor;
import Challenge_Libros.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LibroRepository extends JpaRepository <Libro,Long> {
    //@Query("SELECT e FROM Serie s JOIN s.episodios e WHERE e.titulo ILIKE %:nombreEpisodio%")    //(p. 26)
    //List<Episodio> episodioPorNombre(String nombreEpisodio);

    //@Query("SELECT s FROM Serie s WHERE s.totalTemporadas <= :requisitoTemporada AND s.evaluacion >= :requisitoEvaluacion")
    //List<Serie> seriesPorTempAndEvaluacion(int requisitoTemporada, Double requisitoEvaluacion);

    @Query("SELECT a FROM Libro l JOIN l.autor a")
    List<Autor> todosLosAutores();

    @Query("SELECT a FROM Autor a WHERE :añoUsuario >= a.fechaNacimiento AND :añoUsuario < a.fechaFallecimiento")
    List<Autor> autoresVivos(int añoUsuario);

    @Query("SELECT l FROM Libro l WHERE l.idioma ILIKE %:idioma%")
    List<Libro> librosPorIdioma(String idioma);
}