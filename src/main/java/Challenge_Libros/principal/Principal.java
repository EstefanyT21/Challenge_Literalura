package Challenge_Libros.principal;

import Challenge_Libros.service.ConexionAPI;

import java.io.IOException;

public class Principal {
    ConexionAPI conexionApi = new ConexionAPI();

    public Principal() throws IOException, InterruptedException {
        String json=conexionApi.conectar("https://gutendex.com/books/");
        System.out.println(json);
    }
}
