package com.example.iglesiago.ui.Ubicacion;

import android.content.Intent;
import android.net.Uri;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.google.android.gms.maps.model.LatLng;

public class UbicacionViewModel extends ViewModel {

    private final MutableLiveData<String> mNombre;
    private final MutableLiveData<String> mDireccion;
    private final MutableLiveData<String> mHorarios;
    private final MutableLiveData<LatLng> mPosicion;

    public UbicacionViewModel() {
        mNombre = new MutableLiveData<>("Iglesia Cielos Abiertos U.A.D.");
        mDireccion = new MutableLiveData<>("Bº UOCRA M47, C13, La Punta, San Luis");
        mHorarios = new MutableLiveData<>("Miércoles 20:00 | Viernes 18:30 | Domingos 10:00 y 19:00");

        // Coordenadas exactas actualizadas
        mPosicion = new MutableLiveData<>(new LatLng(-33.1729849, -66.3236367));
    }

    public LiveData<String> getNombre() { return mNombre; }
    public LiveData<String> getDireccion() { return mDireccion; }
    public LiveData<String> getHorarios() { return mHorarios; }
    public LiveData<LatLng> getPosicion() { return mPosicion; }

    public Intent getMapaIntent() {
        LatLng pos = mPosicion.getValue();
        // Genera el link de navegación para Google Maps
        String uri = "geo:" + pos.latitude + "," + pos.longitude + "?q=" + Uri.encode(mNombre.getValue());
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        intent.setPackage("com.google.android.apps.maps");
        return intent;
    }
}