package cdg.DesafioLiteratura.Literatura.Modelo;

import java.util.List;
import java.util.Map;

public class Libro {
    private int id;
    private String titulo;
    private List<String> sujetos;
    private List<Persona> autores;
    private List<String> resumen;
    private List<Persona> traductores;
    private List<String> estanteria;//bookshelves
    private List<String> idiomas;
    private Boolean copyright; // puede ser null
    private String media_type;
    private Map<String, String> formats; // formato MIME → URL
    private int numeroDeDescargas;

    public Libro(DatosLibro libro) {
        this.titulo = libro.titulo();
        this.sujetos = libro.sujetos();
        this.autores = libro.autores();
        this.resumen = libro.resumen();
        this.traductores = libro.traductores();
        this.estanteria = libro.estanteria();
        this.idiomas = libro.idiomas();
        this.copyright = libro.copyright();
        this.media_type = libro.media_type();
        this.formats = libro.formats();
        this.numeroDeDescargas = libro.numeroDeDescargas();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<String> getSujetos() {
        return sujetos;
    }

    public void setSujetos(List<String> sujetos) {
        this.sujetos = sujetos;
    }



    public List<String> getResumen() {
        return resumen;
    }

    public void setResumen(List<String> resumen) {
        this.resumen = resumen;
    }

    public List<Persona> getAutores() {
        return autores;
    }

    public void setAutores(List<Persona> autores) {
        this.autores = autores;
    }

    public List<Persona> getTraductores() {
        return traductores;
    }

    public void setTraductores(List<Persona> traductores) {
        this.traductores = traductores;
    }

    public List<String> getEstanteria() {
        return estanteria;
    }

    public void setEstanteria(List<String> estanteria) {
        this.estanteria = estanteria;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public Boolean getCopyright() {
        return copyright;
    }

    public void setCopyright(Boolean copyright) {
        this.copyright = copyright;
    }

    public String getMedia_type() {
        return media_type;
    }

    public void setMedia_type(String media_type) {
        this.media_type = media_type;
    }

    public Map<String, String> getFormats() {
        return formats;
    }

    public void setFormats(Map<String, String> formats) {
        this.formats = formats;
    }

    public int getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(int numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    @Override
    public String toString() {

        return "---- LIBRO ---- \n" +
                " Titulo: " + titulo  +"\n"+
                " Autor: " + autores.get(0).name() +"\n"+
                " Idioma: " + idiomas +"\n"+
                " Numero de descargas: " + numeroDeDescargas;
    }
}
