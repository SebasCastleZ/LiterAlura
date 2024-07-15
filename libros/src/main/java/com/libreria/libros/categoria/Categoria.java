package com.libreria.libros.categoria;

public enum Categoria{
    EN("en"),
    TL("tl"),
    ES("es");

    private String categoriaIdioma;

    Categoria( String categoriaIdioma) {
        this.categoriaIdioma = categoriaIdioma;
    }

    public static Categoria fromString(String text) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaIdioma.equalsIgnoreCase(text)) {
                System.out.println("categoria encnotrada: " + categoria);
                return categoria;
            }
        }
        throw new IllegalArgumentException("Ninguna categoria encontrada: " + text);
    }
    
    
}
