package com.libreria.odts;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public record Datoslibros(
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<DatosAutor> autors,
        @JsonAlias("languages") List<String> idiomas) {
}
