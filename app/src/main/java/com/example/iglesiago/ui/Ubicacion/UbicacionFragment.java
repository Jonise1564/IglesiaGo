package com.example.iglesiago.ui.Ubicacion;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.iglesiago.databinding.FragmentUbicacionBinding;

public class UbicacionFragment extends Fragment {

    private FragmentUbicacionBinding binding;
    private UbicacionViewModel vm;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        vm = new ViewModelProvider(this).get(UbicacionViewModel.class);
        binding = FragmentUbicacionBinding.inflate(inflater, container, false);

        // El Fragment solo setea lo que el VM le dice
        vm.getNombre().observe(getViewLifecycleOwner(), binding.tvNombre::setText);
        vm.getDireccion().observe(getViewLifecycleOwner(), binding.tvDireccion::setText);
        vm.getHorarios().observe(getViewLifecycleOwner(), binding.tvHorarios::setText);

        // La acción del botón también se apoya en la lógica del VM
        binding.btnComoLlegar.setOnClickListener(v -> {
            Intent mapIntent = vm.getMapaIntent();
            // Verificamos si el celu tiene Maps o navegador
            if (mapIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                startActivity(mapIntent);
            } else {
                // Si no tiene Maps, intentamos abrir sin el setPackage para que use el browser
                mapIntent.setPackage(null);
                startActivity(mapIntent);
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}