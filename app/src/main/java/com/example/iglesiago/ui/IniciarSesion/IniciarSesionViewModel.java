package com.example.iglesiago.ui.IniciarSesion;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class IniciarSesionViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public IniciarSesionViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is iniciar sesion fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
