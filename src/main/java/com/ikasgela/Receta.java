package com.ikasgela;

import java.util.HashMap;
import java.util.Objects;

public class Receta {
    private int id;
    private String titulo;
    private String instrucciones;

    //Asociacion
    private HashMap<String, String> ingredientes = new HashMap<>();

    //Constructor


    public Receta(int id, String titulo, String instrucciones) {
        this.id = id;
        this.titulo = titulo;
        this.instrucciones = instrucciones;
    }

    public Receta(int id, String titulo, String instrucciones, HashMap<String, String> ingredientes) {
        this.id = id;
        this.titulo = titulo;
        this.instrucciones = instrucciones;
        this.ingredientes = ingredientes;
    }

    public Receta(String titulo, String instrucciones, HashMap<String, String> ingredientes) {
        this.titulo = titulo;
        this.instrucciones = instrucciones;
        this.ingredientes = ingredientes;
    }

    public Receta(String titulo, String instrucciones) {
        this.titulo = titulo;
        this.instrucciones = instrucciones;
    }

    public Receta() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(String instrucciones) {
        this.instrucciones = instrucciones;
    }

    public HashMap<String, String> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(HashMap<String, String> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public void addIngredientes(String ingrediente, String cantidad) {
        ingredientes.put(ingrediente, cantidad);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Receta receta = (Receta) o;
        return Objects.equals(titulo, receta.titulo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo);
    }

    @Override
    public String toString() {
        return "Receta{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", instrucciones='" + instrucciones + '\'' +
                '}';
    }
}
