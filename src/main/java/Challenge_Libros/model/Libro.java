package Challenge_Libros.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name="libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(unique = true)
    String titulo;

    @OneToMany(mappedBy = "libro")
    List<Autor> autor;

    List<String> idioma;
    Integer descargas;
}
