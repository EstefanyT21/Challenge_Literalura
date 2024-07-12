package Challenge_Libros.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;

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
}
