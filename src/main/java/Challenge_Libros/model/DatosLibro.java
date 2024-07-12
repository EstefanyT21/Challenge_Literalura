package Challenge_Libros.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)

public record DatosLibro(
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<Autores> autor,
        //@JsonAlias("languages") String idioma,
        @JsonAlias("download_count") Integer descargas

){
    @Override
    public String toString() {
        return "Título: "+titulo+"\nAutor: "+"\nIdioma: "+"\nNúmero de descargas: "+descargas;
    }
}
