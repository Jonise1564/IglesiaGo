package com.example.iglesiago.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class LoginRequest implements Serializable {

    @SerializedName("email") // Esto asegura que el JSON diga "email"
    private String email;

    @SerializedName("password") // Esto asegura que el JSON diga "password"
    private String password;

    public LoginRequest() {
    }

    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}