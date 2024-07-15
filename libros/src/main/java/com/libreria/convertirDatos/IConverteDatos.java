package com.libreria.convertirDatos;

public interface IConverteDatos {
    //la T significa tipo de dato generico
    <T> T obtenerDatos(String json, Class<T> clase);
}
