package Challenge_Libros.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    Libro libro;

    String autor;
    Integer fechaNacimiento;
    Integer fechaFallecimiento;

    public Autor(DatosAutor a) {
        autor=a.autor();
        fechaNacimiento=a.fechaNacimiento();
        fechaFallecimiento=a.fechaFallecimiento();
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }
}
