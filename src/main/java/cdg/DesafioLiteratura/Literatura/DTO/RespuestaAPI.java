package cdg.DesafioLiteratura.Literatura.DTO;

import java.util.List;

public record RespuestaAPI(int count,
                           String next,
                           String previous,
                           List<DatosLibros> results) {
}
