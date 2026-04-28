package com.example.iglesiago.ui.Ubicacion;

import android.content.Intent;
import android.net.Uri;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UbicacionViewModel extends ViewModel {

    private final MutableLiveData<String> mNombre;
    private final MutableLiveData<String> mDireccion;
    private final MutableLiveData<String> mHorarios;
    private final MutableLiveData<Double> mLatitud;
    private final MutableLiveData<Double> mLongitud;

    public UbicacionViewModel() {
        mNombre = new MutableLiveData<>("IglesiaGo San Luis");
        mDireccion = new MutableLiveData<>("Av. España y Colón, D5700 San Luis");
        mHorarios = new MutableLiveData<>("Reuniones: Domingos 10:00 y 19:00 hs");

        // Coordenadas fijas de la Iglesia
        mLatitud = new MutableLiveData<>(-33.3020);
        mLongitud = new MutableLiveData<>(-66.3368);
    }

    public LiveData<String> getNombre() { return mNombre; }
    public LiveData<String> getDireccion() { return mDireccion; }
    public LiveData<String> getHorarios() { return mHorarios; }

    // Lógica para generar el Intent de navegación
    public Intent getMapaIntent() {
        String uri = "geo:" + mLatitud.getValue() + "," + mLongitud.getValue()
                + "?q=" + Uri.encode(mNombre.getValue());
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        intent.setPackage("com.google.android.apps.maps");
        return intent;
    }
}