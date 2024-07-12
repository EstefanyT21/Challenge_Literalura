package Challenge_Libros.principal;

import Challenge_Libros.model.Datos;
import Challenge_Libros.model.DatosLibro;
import Challenge_Libros.service.ConexionAPI;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    ConexionAPI conexionApi = new ConexionAPI();
    ObjectMapper objectMapper = new ObjectMapper();
    Scanner lectura=new Scanner(System.in);

    public void menu() throws IOException, InterruptedException {
        int opc = 1;

        System.out.println("BIENVENIDO A LITERALURA");
        while (opc!=0){
            System.out.println("""
                    \nSelecciona la acción a realizar:
                    1. Buscar un libro
                    2. Ver el historial de bpusqueda
                    3. Listar los autores de los libros consultados
                    4. Listar autores vivos en un determinado año
                    5. Listar libros por idioma
                    0. Salir
                    """);
            opc = lectura.nextInt();
            lectura.nextLine();

            switch (opc){
                case 1:
                    buscarLibro();
                    break;
                case 2:
                    buscarLibrosGuardados();
                    break;
                case 0:
                    System.out.println("Proceso finalizado. \nGracias por utilizar nuestro servicio");
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private void buscarLibro() throws IOException, InterruptedException {

        System.out.println("Ingrese el autor del libro que desea buscar");
        var tituloUsuario = lectura.nextLine();

        String json = conexionApi.conectar("https://gutendex.com/books/?search=" + tituloUsuario.replace(" ","+"));
        var datosBusqueda = objectMapper.readValue(json, Datos.class);
        Optional<DatosLibro> libroBuscado = datosBusqueda.libros().stream()
                .filter(l -> l.titulo().toUpperCase().contains(tituloUsuario.toUpperCase()))
                .findFirst();
        if(libroBuscado.isPresent()){
            System.out.println(libroBuscado.get());
        }else {
            System.out.println("Libro no encontrado");
        }
    }
    public void buscarLibrosGuardados(){

    }
}
