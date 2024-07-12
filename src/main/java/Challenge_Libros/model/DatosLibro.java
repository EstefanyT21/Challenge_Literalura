package Challenge_Libros.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)

public record DatosLibro(
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<Autores> autor,
        @JsonAlias("languages") List<String> idioma,
        @JsonAlias("download_count") Integer descargas

){
    @Override
    public String toString() {
        return "Libro: "+titulo+"\nAutor: "+autor.get(0).nombre()+"\nIdioma: "+idioma.get(0)+"\nNúmero de descargas: "+descargas;
    }
}
