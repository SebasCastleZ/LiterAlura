package com.libreria.repositorio;

import com.libreria.libros.Libros;
import com.libreria.libros.categoria.Categoria;
import com.libreria.odts.Datoslibros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LibrosRepositorio extends JpaRepository<Libros, Long> {
    Optional<Libros> findByTituloContainsIgnoreCase(String nombreAutor);

    @Query("SELECT l FROM Libros l WHERE  l.ano_nacimiento >= :anoElegido")
    List<Libros>librosDeAutoresPorRangoDeAno(Double anoElegido);

    List<Libros> findByIdioma(Categoria categoria);
}
