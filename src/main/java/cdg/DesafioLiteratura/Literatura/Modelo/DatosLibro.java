package cdg.DesafioLiteratura.Literatura.Modelo;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro(
        @JsonAlias("id") int id,
        @JsonAlias("title") String titulo,
        @JsonAlias("subjects") List<String> sujetos,
        @JsonAlias("authors") List<Persona> autores,
        @JsonAlias("summaries") List<String> resumen,
        @JsonAlias("translators") List<Persona> traductores,
        @JsonAlias("bookshelves") List<String> estanteria,//bookshelves
        @JsonAlias("languages") List<String> idiomas,
        @JsonAlias("copyright") Boolean copyright, // puede ser null
        @JsonAlias("media_type") String media_type,
        @JsonAlias("formats") Map<String, String> formats, // formato MIME → URL
        @JsonAlias("download_count") int numeroDeDescargas
    ) {

}
