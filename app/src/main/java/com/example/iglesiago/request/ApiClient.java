package com.example.iglesiago.request;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    // Asegurate de que esta IP sea la correcta de tu PC hoy
   // public final static String URL_BASE = "http://192.168.88.104:5207/";
    public final static String URL_BASE = "http://192.168.18.14:5207/";


    private static IglesiaGoService iglesiaGoService;

    public static IglesiaGoService getApiIglesiaGo() {
        if (iglesiaGoService == null) {
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                    .setLenient()
                    .create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(URL_BASE)
                    // ELIMINAMOS ScalarsConverterFactory para que no choque con los objetos JSON
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            iglesiaGoService = retrofit.create(IglesiaGoService.class);
        }
        return iglesiaGoService;
    }

    public static void guardarToken(Context context, String token) {
        SharedPreferences sp = context.getSharedPreferences("token_pref", Context.MODE_PRIVATE);
        // Ya no necesitas limpiar comillas porque Gson lo hace solo al leer el objeto
        sp.edit().putString("token", "Bearer " + token).apply();
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