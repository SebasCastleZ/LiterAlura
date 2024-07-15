package com.libreria.libros;


import com.libreria.libros.categoria.Categoria;
import com.libreria.odts.DatosAutor;
import com.libreria.odts.Datoslibros;
import jakarta.persistence.*;
import org.springframework.data.util.QTypeContributor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

@Entity
@Table(name = "libros")
public class Libros {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @Column(unique = true)
    private String titulo;
    private String autor;
    @Enumerated(EnumType.STRING)
    private Categoria idioma;
    
    private double ano_nacimiento;
    private double ano_fallecimineto;

    public Libros() {
    }

    public Libros(Datoslibros datoslibros) {
        this.titulo = datoslibros.titulo();
        this.autor = datoslibros.autors() != null ? String.join(", ", datoslibros.autors().stream().map(datosAutor -> datosAutor.nombre()).toList()) : "";
        this.ano_nacimiento = OptionalDouble.of(Double.valueOf(datoslibros.autors().get(0).fechaDeNacimineto())).orElse(0);
        this.ano_fallecimineto = OptionalDouble.of(Double.valueOf(datoslibros.autors().get(0).fechaDeFallecimineto())).orElse(0);
        System.out.println(Categoria.fromString(datoslibros.idiomas().get(0)));
        this.idioma =  Categoria.fromString(datoslibros.idiomas().get(0));
        //this.idioma = Categoria.fromString(datoslibros.get().idiomas().split(",")[0].trim());

    }

    @Override
    public String toString() {
        return
                "titulo='" + titulo + '\'' +
                        ", Autor=" + autor +
                        ", idioma=" + idioma;
    }

    //getter & setters


    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}
