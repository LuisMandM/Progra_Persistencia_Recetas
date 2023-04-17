package com.ikasgela;

public class Ingrediente {
    private String nombre;
    private int id;

    public Ingrediente(String nombre, int id) {
        this.nombre = nombre;
        this.id = id;
    }

    public Ingrediente() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Ingrediente{" +
                "nombre='" + nombre + '\'' +
                ", id=" + id +
                '}';
    }
}
