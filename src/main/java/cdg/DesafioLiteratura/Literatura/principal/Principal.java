package cdg.DesafioLiteratura.Literatura.principal;

import cdg.DesafioLiteratura.Literatura.Modelo.DatosLibro;
import cdg.DesafioLiteratura.Literatura.Modelo.Libro;
import cdg.DesafioLiteratura.Literatura.Modelo.RespuestaAPI;
import cdg.DesafioLiteratura.Literatura.service.ConsumoDeApi;
import cdg.DesafioLiteratura.Literatura.service.ConvierteDatos;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Principal {

    private Scanner teclado = new Scanner(System.in);
    private ConvierteDatos conversor = new ConvierteDatos();
    private ConsumoDeApi api = new ConsumoDeApi();

    public void ejecutar() {

        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    Bienvenido -> Seleccione una opcion: 
                    1 - Buscar libro por titulo
                    
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibroPorTitulo();
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
        String tituloDelLibro;
        String tituloCodificado;
        System.out.println("Ingrese nombre del libro: ");
        tituloDelLibro= teclado.nextLine();
        tituloCodificado = URLEncoder.encode(tituloDelLibro, StandardCharsets.UTF_8);
        //System.out.println(tituloCodificado);

        var json = api.obtenerDatos("http://gutendex.com/books/?search="+tituloCodificado);
        RespuestaAPI respuestaAPI = conversor.obtenerDatos(json, RespuestaAPI.class);
        DatosLibro datosLibro = respuestaAPI.results().get(0);
        //System.out.println(datosLibro);
        Libro libro = new Libro(datosLibro);
        System.out.println(libro.toString()+"\n");

    }

}
