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
                    2. Ver el historial de búsqueda
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
                case 3:
                    buscarAutores();
                    break;
                case 4:
                    listarAutoresVivos();
                    break;
                case 5:
                    listarPorIdioma();
                    break;
                case 0:
                    System.out.println("Proceso finalizado. \nGracias por utilizar nuestro servicio");
                    break;
                default:
                    System.out.println("Opción inválida");
                    break;
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

            List<DatosAutor> datosAutor = datoLibroEncontrado.autor().stream()
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

    public void buscarAutores(){
        List<Autor> autoresEncontrados = repositorio.todosLosAutores();
        autoresEncontrados.forEach(System.out::println);
    }
    public void listarAutoresVivos(){
        System.out.println("Ingresa el año: ");
        List<Autor> autoresEncontrados = repositorio.autoresVivos(lectura.nextInt());
        autoresEncontrados.forEach(System.out::println);
    }
    public void listarPorIdioma(){
        while (true){
            System.out.println("""
                Por favor escribe las siglas correpondientes al idioma a consultar:
                (es) Español
                (en) Inglés
                (fr) Francés
                (pt) Postugués
                """);
            String opc = lectura.nextLine();
            if(opc.contains("es")|opc.contains("en")|opc.contains("fr")|opc.contains("pt")){
                List<Libro> librosEncontrados = repositorio.librosPorIdioma(opc);
                librosEncontrados.forEach(System.out::println);
                break;
            }
            else{
                System.out.println("Opción inválida");
            }
        }

    }
}
