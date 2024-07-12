package Challenge_Libros.principal;

import Challenge_Libros.model.DatosLibro;
import Challenge_Libros.service.ConexionAPI;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Scanner;

public class Principal {
    ConexionAPI conexionApi = new ConexionAPI();
    ObjectMapper objectMapper = new ObjectMapper();
    Scanner lectura=new Scanner(System.in);

    public Principal() throws IOException, InterruptedException {
        String json=conexionApi.conectar("https://gutendex.com/books/1513/");
        System.out.println(json);

        DatosLibro datosLibro = objectMapper.readValue(json, DatosLibro.class);
        System.out.println(datosLibro);

    }
}
