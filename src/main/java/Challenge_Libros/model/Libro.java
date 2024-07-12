package Challenge_Libros.model;

import jakarta.persistence.*;
import java.util.List;
import java.util.Optional;

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

    @Transient
    Integer idApi;

    public Libro(DatosLibro libroBuscado) {
        titulo=libroBuscado.titulo();
        idioma=libroBuscado.idioma();
        descargas=libroBuscado.descargas();
        idApi= libroBuscado.idApi();
    }

    public void setAutor(List<Autor> autor) {
        autor.forEach(e->e.setLibro(this));
        this.autor = autor;
    }
}
