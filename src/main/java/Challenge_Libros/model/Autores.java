package Challenge_Libros.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)

public record Autores (
        @JsonAlias("name") String autor,
        @JsonAlias("birth_year")LocalDate fechaNacimiento,
        @JsonAlias("death_year")LocalDate fechaFallecimiento
        ){
}
