package com.ikasgela;

import java.util.Objects;

public class Ingrediente {
    private String nombre;
    private int id;

    public Ingrediente(String nombre, int id) {
        this.nombre = nombre;
        this.id = id;
    }

    public Ingrediente(String nombre) {
        this.nombre = nombre;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingrediente that = (Ingrediente) o;
        return Objects.equals(nombre, that.nombre);
    }

    public int getId() {
        return id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }

    @Override
    public String toString() {
        return "Ingrediente{" +
                "nombre='" + nombre + '\'' +
                ", id=" + id +
                '}';
    }
}
