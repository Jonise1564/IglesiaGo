/* package com.example.iglesiago.request;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ApiClient {

    // Cambiar por tu IP local (ej. 192.168.1.XX) si usas celular físico
    // o 10.0.2.2 si usas el emulador de Android Studio
    //public final static String URL_BASE = "http://10.0.2.2:5207/";
    public final static String URL_BASE = "http://192.168.88.104:5207/";
    private static IglesiaGoService iglesiaGoService;

    // Inicializa Retrofit y devuelve la instancia del servicio de la Iglesia
    public static IglesiaGoService getApiIglesiaGo() {
        if (iglesiaGoService == null) {
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                    .setLenient() // Útil para manejar respuestas JSON que a veces vienen con formatos estrictos
                    .create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(URL_BASE)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            iglesiaGoService = retrofit.create(IglesiaGoService.class);
        }
        return iglesiaGoService;
    }

    // Guarda el token JWT obtenido del Login en SharedPreferences
    public static void guardarToken(Context context, String token) {
        SharedPreferences sp = context.getSharedPreferences("token_pref", Context.MODE_PRIVATE);
        // Es una buena práctica agregar "Bearer " aquí o al momento de enviar el Header
        sp.edit().putString("token", "Bearer " + token).apply();
    }

    // Lee el token desde SharedPreferences para enviarlo en los Headers de las peticiones protegidas
    public static String leerToken(Context context) {
        SharedPreferences sp = context.getSharedPreferences("token_pref", Context.MODE_PRIVATE);
        return sp.getString("token", null);
    }

    // Método para borrar el token al cerrar sesión
    public static void borrarToken(Context context) {
        SharedPreferences sp = context.getSharedPreferences("token_pref", Context.MODE_PRIVATE);
        sp.edit().clear().apply();
    }
}*/



package com.example.iglesiago.request;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ApiClient {

    public final static String URL_BASE = "http://192.168.88.104:5207/";
    private static IglesiaGoService iglesiaGoService;

    public static IglesiaGoService getApiIglesiaGo() {
        if (iglesiaGoService == null) {
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                    .setLenient()
                    .create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(URL_BASE)
                    // El orden es correcto: Scalars primero para leer el Token String
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            iglesiaGoService = retrofit.create(IglesiaGoService.class);
        }
        return iglesiaGoService;
    }

    public static void guardarToken(Context context, String token) {
        SharedPreferences sp = context.getSharedPreferences("token_pref", Context.MODE_PRIVATE);

        // LIMPIEZA CRÍTICA: Quitamos comillas sobrantes que Scalars suele agregar
        String tokenLimpio = token.replace("\"", "");

        sp.edit().putString("token", "Bearer " + tokenLimpio).apply();
    }

    public static String leerToken(Context context) {
        SharedPreferences sp = context.getSharedPreferences("token_pref", Context.MODE_PRIVATE);
        return sp.getString("token", null);
    }

    public static void borrarToken(Context context) {
        SharedPreferences sp = context.getSharedPreferences("token_pref", Context.MODE_PRIVATE);
        sp.edit().clear().apply();
    }
}