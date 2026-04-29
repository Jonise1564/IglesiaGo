package com.example.iglesiago.request;

import com.example.iglesiago.model.Ensenanza;
import com.example.iglesiago.model.Noticia;
import com.example.iglesiago.model.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface IglesiaGoService {

    // --- AUTENTICACIÓN ---

    @POST("Auth/Login")
    Call<String> login(@Body LoginRequest loginRequest);

    @GET("Usuarios/perfil")
    Call<Usuario> obtenerPerfil(@Header("Authorization") String token);


    // --- NOTICIAS (Acceso Público) ---

    @GET("NoticiasApi")
    Call<List<Noticia>> obtenerNoticias();


    // --- ENSEÑANZAS (Acceso Público) ---

    @GET("EnsenanzasApi")
    Call<List<Ensenanza>> obtenerEnsenanzas();



    // --- GESTIÓN DE USUARIO (Acceso Privado) ---

    @PUT("Usuarios/actualizar")
    Call<Usuario> actualizarPerfil(@Header("Authorization") String token, @Body Usuario usuario);

    @FormUrlEncoded
    @PUT("Usuarios/cambiarClave")
    Call<Void> cambiarClave(
            @Header("Authorization") String token,
            @Field("currentPassword") String currentPassword,
            @Field("newPassword") String newPassword
    );
}