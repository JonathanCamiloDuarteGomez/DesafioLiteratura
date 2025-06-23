package cdg.DesafioLiteratura.Literatura.Modelo;

import java.util.List;

public record RespuestaAPI(int count,
                           String next,
                           String previous,
                           List<DatosLibro> results) {
}
