package Challenge_Libros.principal;

import Challenge_Libros.model.*;
import Challenge_Libros.respository.LibroRepository;
import Challenge_Libros.service.ConexionAPI;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    ConexionAPI conexionApi = new ConexionAPI();
    ObjectMapper objectMapper = new ObjectMapper();
    Scanner lectura=new Scanner(System.in);
    LibroRepository repositorio;
    List<Libro> libros;

    public Principal(LibroRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void menu() throws IOException, InterruptedException {
        int opc = 1;

        System.out.println("\nBIENVENIDO A LITERALURA");
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
                    libros.forEach(System.out::println);
                    break;
                case 0:
                    System.out.println("Proceso finalizado. \nGracias por utilizar nuestro servicio");
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private void buscarLibro() throws IOException, InterruptedException {

        System.out.println("Ingrese el título del libro que desea buscar: ");
        var tituloUsuario = lectura.nextLine();
        String json = conexionApi.conectar("https://gutendex.com/books/?search=" + tituloUsuario.replace(" ","+"));
        var datosBusqueda = objectMapper.readValue(json, Datos.class);

        Optional<DatosLibro> libroBuscado = datosBusqueda.libros().stream()
                .filter(l -> l.titulo().toUpperCase().contains(tituloUsuario.toUpperCase()))
                .findFirst();

        if(libroBuscado.isPresent()){

            DatosLibro datoLibroEncontrado =libroBuscado.get();
            Libro libroEncontrado = new Libro(datoLibroEncontrado);

            json = conexionApi.conectar("https://gutendex.com/books/"+ datoLibroEncontrado.idApi()+"/");
            //System.out.println("\nJSON 2 \n"+json);
            DatosLibro datos = objectMapper.readValue(json, DatosLibro.class);
            List<DatosAutor> datosAutor = datos.autor().stream()
                    .collect(Collectors.toList());
            List<Autor> autor = datosAutor.stream()
                    .map(a->new Autor(a))
                            .collect(Collectors.toList());
            libroEncontrado.setAutor(autor);

            if(validar(tituloUsuario)==true){
                System.out.println(libroEncontrado);
                repositorio.save(libroEncontrado);
            }
        }
        else {
            System.out.println("Libro no encontrado");
        }
    }

    public void buscarLibrosGuardados(){
        libros=repositorio.findAll();
    }

    public Boolean validar(String nombre){
        buscarLibrosGuardados();
        Optional<Libro> libro = libros.stream()
                .filter(s->s.getTitulo().toLowerCase().contains(nombre))
                .findFirst();
        if(libro.isPresent()){
            System.out.println("El libro ya existe");
            return false;
        }
        else {
            return true;
        }
    }
}
