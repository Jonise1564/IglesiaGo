package com.example.iglesiago.ui.Devocional;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.iglesiago.model.Ensenanza;
import com.example.iglesiago.request.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DevocionalViewModel extends ViewModel {

    private final MutableLiveData<List<Ensenanza>> mLista;

    public DevocionalViewModel() {
        mLista = new MutableLiveData<>();
    }

    // Getter para que el Fragment observe la lista
    public LiveData<List<Ensenanza>> getListaEnsenanzas() {
        return mLista;
    }

    // Método para conectar con Laragon/API
    public void cargarEnsenanzas() {
        ApiClient.getApiIglesiaGo().obtenerEnsenanzas().enqueue(new Callback<List<Ensenanza>>() {
            @Override
            public void onResponse(Call<List<Ensenanza>> call, Response<List<Ensenanza>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Si la API responde bien, cargamos la lista
                    mLista.setValue(response.body());
                    Log.d("API_DEVOCIONAL", "Enseñanzas cargadas: " + response.body().size());
                } else {
                    Log.e("API_DEVOCIONAL", "Error en respuesta: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Ensenanza>> call, Throwable t) {
                // Si entra acá, revisá que la IP sea la correcta en ApiClient
                Log.e("API_DEVOCIONAL", "Fallo de conexión: " + t.getMessage());
            }
        });
    }
}