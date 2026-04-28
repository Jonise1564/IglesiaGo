package com.example.iglesiago.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Noticia implements Serializable {
    private int id;

    @SerializedName("imagenPortada") // Coincide con el JSON
    private String imagenUrl;

    @SerializedName("cuerpo")        // Coincide con el JSON
    private String contenido;

    @SerializedName("fecha")         // Coincide con el JSON
    private String fechaPublicacion;

    // El título no viene en el JSON que pasaste,
    // si la API no lo manda, podrías usar un pedazo del cuerpo como título.
    private String titulo;

    public Noticia() {}

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getImagenUrl() { return imagenUrl; }
    public void setImagenUrl(String imagenUrl) { this.imagenUrl = imagenUrl; }

    public String getContenido() { return contenido; }
    public void setContenido(String contenido) { this.contenido = contenido; }

    public String getFechaPublicacion() { return fechaPublicacion; }
    public void setFechaPublicacion(String fechaPublicacion) { this.fechaPublicacion = fechaPublicacion; }

    public String getTitulo() {
        // Si el título viene nulo, devolvemos una parte del cuerpo
        if (titulo == null && contenido != null) {
            return contenido.substring(0, Math.min(contenido.length(), 25)) + "...";
        }
        return titulo;
    }
}