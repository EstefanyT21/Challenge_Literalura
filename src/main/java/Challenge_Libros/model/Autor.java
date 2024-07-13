package Challenge_Libros.model;

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

    public Autor() {}

    public Autor(DatosAutor a) {
        autor = a.autor();
        fechaNacimiento = a.fechaNacimiento();
        fechaFallecimiento = a.fechaFallecimiento();
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    @Override
    public String toString() {
        return "----------------"+
                "\nAutor: "+autor+"\nFecha de nacimiento: "+fechaNacimiento+"" +
                "\nFecha de fallecimiento: "+fechaFallecimiento+"\nLibros: "+libro.titulo;
    }
}
