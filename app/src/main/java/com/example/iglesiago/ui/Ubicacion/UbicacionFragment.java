package com.example.iglesiago.ui.Ubicacion;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.iglesiago.R;
import com.example.iglesiago.databinding.FragmentUbicacionBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.MarkerOptions;

// 1. Agregamos el "implements OnMapReadyCallback"
public class UbicacionFragment extends Fragment implements OnMapReadyCallback {

    private FragmentUbicacionBinding binding;
    private UbicacionViewModel vm;
    private GoogleMap mMap;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        vm = new ViewModelProvider(this).get(UbicacionViewModel.class);
        binding = FragmentUbicacionBinding.inflate(inflater, container, false);

        // 2. Inicializamos el Fragmento del Mapa
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map); // Asegurate que este ID esté en tu XML
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        // Observar textos
        vm.getNombre().observe(getViewLifecycleOwner(), binding.tvNombre::setText);
        vm.getDireccion().observe(getViewLifecycleOwner(), binding.tvDireccion::setText);
        vm.getHorarios().observe(getViewLifecycleOwner(), binding.tvHorarios::setText);

        binding.btnComoLlegar.setOnClickListener(v -> {
            Intent mapIntent = vm.getMapaIntent();
            startActivity(mapIntent);
        });

        return binding.getRoot();
    }

    // 3. Este método es el que mueve la cámara y pone el Pin
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        // Configuraciones de UI del mapa
        mMap.getUiSettings().setZoomControlsEnabled(true);

        // Observamos la posición desde el VM para ubicar el mapa
        vm.getPosicion().observe(getViewLifecycleOwner(), latLng -> {
            if (latLng != null) {
                mMap.clear();
                mMap.addMarker(new MarkerOptions().position(latLng).title(vm.getNombre().getValue()));

                // ESTO es lo que hace que viaje de África a La Punta
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16f));
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}