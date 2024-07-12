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

    @OneToMany(mappedBy = "libro",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Autor> autor;

    List<String> idioma;
    Integer descargas;

    @Transient
    Integer idApi;

    public  Libro(){}

    public Libro(DatosLibro libroBuscado) {
        titulo=libroBuscado.titulo();
        idioma=libroBuscado.idioma();
        descargas=libroBuscado.descargas();
        idApi= libroBuscado.idApi();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setAutor(List<Autor> autor) {
        autor.forEach(a->a.setLibro(this));
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "Libro: "+titulo+"\nAutor: "+autor.get(0).autor+
                "\nIdioma: "+idioma.get(0)+"\nNúmero de descargas: "+descargas+
                "\n----------------";
    }
}
