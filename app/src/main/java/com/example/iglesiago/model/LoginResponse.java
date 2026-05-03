
package com.example.iglesiago.model;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("token")
    private String token;

    @SerializedName("message")
    private String message;

    @SerializedName("nombre")
    private String nombre;

    @SerializedName("rol")
    private String rol;

    // Getters
    public String getToken() { return token; }
    public String getNombre() { return nombre; }
}