package com.libreria.odts;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
//sirve para trar datos de la API incluye las getters y setters
public record Datos(
     @JsonAlias("results") List<Datoslibros> libros
) {


}
