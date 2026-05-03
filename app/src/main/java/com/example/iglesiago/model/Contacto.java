package com.example.iglesiago.model;

import com.google.gson.annotations.SerializedName;

public class Contacto {
    @SerializedName("nombreRemitente")
    private String nombre;

    @SerializedName("telefono")
    private String telefono;

    @SerializedName("mail")
    private String mail;

    @SerializedName("mensaje")
    private String mensaje;

    public Contacto(String nombre, String telefono, String mail, String mensaje) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.mail = mail;
        this.mensaje = mensaje;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}