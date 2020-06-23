package com.example.components.models;

import java.io.Serializable;

public class Peliculas implements Serializable {
    private String image;
    private String nombre;

    public Peliculas(String image, String nombre) {
        this.image = image;
        this.nombre = nombre;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
