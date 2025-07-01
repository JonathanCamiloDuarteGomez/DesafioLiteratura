package cdg.DesafioLiteratura.Literatura.Modelo;

import cdg.DesafioLiteratura.Literatura.DTO.DatosLibros;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> idiomas;

    private int numeroDeDescargas;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;

    public Libro() {
    }

    public Libro(DatosLibros libro) {
        this.titulo = libro.titulo();
        this.idiomas = libro.idiomas();
        this.numeroDeDescargas = libro.numeroDeDescargas();

    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public int getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(int numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "---- LIBRO ---- \n" +
                " Titulo: " + titulo + "\n" +
                " Autor: " + (autor != null ? autor.getNombre() : "Desconocido") + "\n" +
                " Idioma: " + idiomas + "\n" +
                " Numero de descargas: " + numeroDeDescargas;
    }
}
