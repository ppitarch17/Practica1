package Modelo;

import java.io.Serializable;

public class Etiqueta implements Serializable {

    enum valorEtiquta{

    }

    private String nombre;
    public Etiqueta(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "Modelo.Etiqueta{" +
                "nombre='" + nombre + '\'' +
                '}';
    }
}
