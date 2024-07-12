package Challenge_Libros.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)

public record DatosLIbro (
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<Autores> autores,
        @JsonAlias("languages") String idioma,
        @JsonAlias("download_count") Integer descargas

){
}
