package cdg.DesafioLiteratura.Literatura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvierteDatos implements  IConvierteDatos{
    /*
    Se declara un objeto de la clase ObjectMapper,
    que es la clase principal de la biblioteca Jackson
    para la manipulación de JSON. Este objeto se utiliza
    para convertir entre JSON y objetos Java.
     */
    //Sirven para lo mismo, gson y json
    private ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public <T> T obtenerDatos(String json, Class<T> clase) {
        try {

            return objectMapper.readValue(json,clase);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
