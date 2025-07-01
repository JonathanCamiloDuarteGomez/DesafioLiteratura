package cdg.DesafioLiteratura.Literatura.principal;

import cdg.DesafioLiteratura.Literatura.DTO.DatosAutor;
import cdg.DesafioLiteratura.Literatura.DTO.DatosLibros;
import cdg.DesafioLiteratura.Literatura.DTO.RespuestaAPI;
import cdg.DesafioLiteratura.Literatura.Modelo.Autor;
import cdg.DesafioLiteratura.Literatura.Modelo.Libro;
import cdg.DesafioLiteratura.Literatura.repository.AutorRepository;
import cdg.DesafioLiteratura.Literatura.repository.LibroRepository;
import cdg.DesafioLiteratura.Literatura.service.ConsumoDeApi;
import cdg.DesafioLiteratura.Literatura.service.ConvierteDatos;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;

public class Principal {

    private Scanner teclado = new Scanner(System.in);
    private ConvierteDatos conversor = new ConvierteDatos();
    private ConsumoDeApi api = new ConsumoDeApi();
    private LibroRepository repositorio;
    private AutorRepository autorRepository;

    public Principal(LibroRepository repository, AutorRepository autorRepository) {
        this.repositorio = repository;
        this.autorRepository = autorRepository;
    }

    public void ejecutar() {

        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    
                    ****** Bienvenido Sistema de Literatura *****
                     
                    1 - Buscar libro por titulo
                    2 - listar libros registrados
                    3 - listar autores registrados
                    4 - listar autores vivos en un determinado año
                    5 - lista de libros por idioma
                    0 - Salir
                    Seleccione una opcion: 
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:
                    listarLibros();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivos();
                    break;
                case 5:
                    listarLibrosPorIdioma();
                    break;
                case 0:
                    System.out.println(" Fin de la aplicacion...");
                    break;
                default:
                    System.out.println("Opción inválida ");
            }
        }


    }



    private void buscarLibroPorTitulo() {
        System.out.println("Ingrese nombre del libro: ");
        String tituloDelLibro = teclado.nextLine();
        String tituloCodificado = URLEncoder.encode(tituloDelLibro, StandardCharsets.UTF_8);

        try {
            var json = api.obtenerDatos("http://gutendex.com/books/?search=" + tituloCodificado);
            RespuestaAPI respuestaAPI = conversor.obtenerDatos(json, RespuestaAPI.class);

            if (respuestaAPI.results().isEmpty()) {
                System.out.println("No se encontraron resultados para ese título.");
                return;
            }

            DatosLibros datosLibros = respuestaAPI.results().get(0);
            Libro libro = new Libro(datosLibros);

            // Procesar autor
            if (!datosLibros.autores().isEmpty()) {
                DatosAutor datosAutor = datosLibros.autores().get(0);
                Optional<Autor> autorExistente = autorRepository.findByNombre(datosAutor.nombre());

                Autor autor = autorExistente.orElseGet(() -> {
                    Autor nuevo = new Autor(datosAutor);
                    return autorRepository.save(nuevo);
                });

                libro.setAutor(autor);
            }

            repositorio.save(libro);
            //System.out.println(libro.toString());

        } catch (Exception e) {
            System.out.println("Ocurrió un error al buscar el libro: " + e.getMessage());
        }
    }

    private void listarLibros() {
        repositorio.listadoDeLibrosConsultados().forEach(System.out::println);
    }
    private void listarAutoresRegistrados() {
        autorRepository.listadoDeAutoresConsultados().forEach(a -> {
            System.out.println("Autor: " + a.getNombre());
            System.out.println("Fecha de nacimiento: " + a.getFechaDeNacimiento());
            System.out.println("Fecha de fallecimiento: " + a.getFechaDeFallecimiento());
            System.out.println("Libros: ");
            a.getLibros().forEach(l -> System.out.println(" - " + l.getTitulo()));
        });
    }

    private void listarAutoresVivos() {
        Double dato;
        System.out.println("Ingrese el año vivo de autor(es) que desea buscar ");
        dato = teclado.nextDouble();
        try{
            if(!dato.isNaN()){
                autorRepository.vivos(dato).forEach(a -> {
                    System.out.println("\n");
                    System.out.println("Autor: " + a.getNombre());
                    System.out.println("Fecha de nacimiento: " + a.getFechaDeNacimiento());
                    System.out.println("Fecha de fallecimiento: " + a.getFechaDeFallecimiento());
                    System.out.println("Libros: ");
                    a.getLibros().forEach(l -> System.out.println(" - " + l.getTitulo()));
                });
            }else{
                System.out.println("ingrese un dato");
            }
        }catch (IllegalArgumentException i){
            System.out.println(i.getMessage());
        }

    }
    private void listarLibrosPorIdioma() {
        System.out.println("""
                Ingrese el idioma para buscar los libros:
                es- español
                en- inglés
                fr- francés
                pt- portugués
                """);


        String entradaLenguaje = teclado.nextLine().toLowerCase(); // Convertir a minúsculas

        Set<String> idiomasValidos = Set.of("es", "en", "fr", "pt"); // Crear un conjunto de idiomas válidos

        if (idiomasValidos.contains(entradaLenguaje)) { // Verificar si la entrada está en el conjunto
            repositorio.listadoDeLibrosPorIdioma(entradaLenguaje).forEach(System.out::println);
        } else {
            System.out.println("Idioma desconocido");
        }


    }

}
