package com.example.iglesiago.ui.IniciarSesion;

import android.app.Application;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.iglesiago.model.LoginResponse;
import com.example.iglesiago.request.ApiClient;
import com.example.iglesiago.request.LoginRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IniciarSesionViewModel extends AndroidViewModel {

    private MutableLiveData<String> mError = new MutableLiveData<>();
    private MutableLiveData<Boolean> mLoginExitoso = new MutableLiveData<>();

    public IniciarSesionViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<String> getError() {
        return mError;
    }

    public LiveData<Boolean> getLoginExitoso() {
        return mLoginExitoso;
    }

    public void login(String email, String password) {
        LoginRequest loginRequest = new LoginRequest(email, password);

        // Cambiamos Callback<String> por Callback<LoginResponse>
        ApiClient.getApiIglesiaGo().login(loginRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Ahora podemos sacar el token y el nombre del objeto
                    String token = response.body().getToken();
                    String nombre = response.body().getNombre();

                    // Guardamos el token en SharedPreferences
                    ApiClient.guardarToken(getApplication(), token);

                    // Avisamos a la vista que el login fue correcto
                    mLoginExitoso.setValue(true);
                  // Toast.makeText(getApplication(), "¡Bienvenido " + nombre + "!", Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplication(), "¡Bienvenido!", Toast.LENGTH_SHORT).show();

                } else {
                    mError.setValue("Email o contraseña incorrectos");
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                mError.setValue("Error de conexión: " + t.getMessage());
            }
        });
    }
}