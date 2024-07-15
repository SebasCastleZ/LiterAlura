package com.libreria.convertirDatos;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//implementa la interfas com.challenge.Libros.service.IConverteDatos con implements
public class ConvierteDatos implements IConverteDatos {
    private ObjectMapper ObjectMapper = new ObjectMapper();

    //sobre-escritura de la interfas IConvierteDatos (contrato)
    @Override
    public <T> T obtenerDatos(String json, Class<T> clase) {
        try {
            return ObjectMapper.readValue(json,clase);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
