package com.example.iglesiago.ui.Inicio;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.iglesiago.model.Noticia;
import com.example.iglesiago.request.ApiClient;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InicioViewModel extends ViewModel {

    private MutableLiveData<List<Noticia>> mLista;

    public InicioViewModel() {
        mLista = new MutableLiveData<>();
    }

    public LiveData<List<Noticia>> getListaNoticias() {
        return mLista;
    }

    public void cargarNoticias() {
        ApiClient.getApiIglesiaGo().obtenerNoticias().enqueue(new Callback<List<Noticia>>() {
            @Override
            public void onResponse(Call<List<Noticia>> call, Response<List<Noticia>> response) {
                if (response.isSuccessful()) {
                    mLista.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Noticia>> call, Throwable t) {
                // Manejar error de conexión aquí
            }
        });
    }
}