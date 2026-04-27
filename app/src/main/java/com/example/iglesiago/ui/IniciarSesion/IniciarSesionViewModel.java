package com.example.iglesiago.ui.IniciarSesion;

import android.app.Application;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.iglesiago.request.ApiClient;
import com.example.iglesiago.request.LoginRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// Cambiamos a AndroidViewModel para poder acceder al Context y guardar el Token
public class IniciarSesionViewModel extends AndroidViewModel {

    private MutableLiveData<String> mError;
    private MutableLiveData<Boolean> mLoginExitoso;

    public IniciarSesionViewModel(@NonNull Application application) {
        super(application);
        mError = new MutableLiveData<>();
        mLoginExitoso = new MutableLiveData<>();
    }

    public LiveData<String> getError() {
        return mError;
    }

    public LiveData<Boolean> getLoginExitoso() {
        return mLoginExitoso;
    }

    public void login(String email, String password) {
        LoginRequest loginRequest = new LoginRequest(email, password);

        ApiClient.getApiIglesiaGo().login(loginRequest).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String token = response.body();

                    // Guardamos el token en SharedPreferences
                    ApiClient.guardarToken(getApplication(), token);

                    // Avisamos a la vista que el login fue correcto
                    mLoginExitoso.setValue(true);
                    Toast.makeText(getApplication(), "¡Bienvenido!", Toast.LENGTH_SHORT).show();
                } else {
                    mError.setValue("Email o contraseña incorrectos");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                mError.setValue("Error de conexión: " + t.getMessage());
            }
        });
    }
}
