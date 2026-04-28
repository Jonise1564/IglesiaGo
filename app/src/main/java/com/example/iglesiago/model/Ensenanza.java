package com.example.iglesiago.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Ensenanza implements Serializable {
    private int id;

    @SerializedName("titulo")
    private String titulo;

    @SerializedName("contenido")
    private String contenido;

    @SerializedName("videoUrl") // Coincide con el JSON de la API
    private String videoUrl;

    @SerializedName("fechaPublicacion")
    private String fechaPublicacion;

    public Ensenanza() {}

    public Ensenanza(int id, String titulo, String contenido, String videoUrl, String fechaPublicacion) {
        this.id = id;
        this.titulo = titulo;
        this.contenido = contenido;
        this.videoUrl = videoUrl;
        this.fechaPublicacion = fechaPublicacion;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getContenido() { return contenido; }
    public void setContenido(String contenido) { this.contenido = contenido; }

    public String getVideoUrl() { return videoUrl; }
    public void setVideoUrl(String videoUrl) { this.videoUrl = videoUrl; }

    public String getFechaPublicacion() { return fechaPublicacion; }
    public void setFechaPublicacion(String fechaPublicacion) { this.fechaPublicacion = fechaPublicacion; }
}