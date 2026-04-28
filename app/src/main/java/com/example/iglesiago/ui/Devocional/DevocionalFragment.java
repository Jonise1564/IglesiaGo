package com.example.iglesiago.ui.Devocional;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.iglesiago.databinding.FragmentDevocionalBinding;

public class DevocionalFragment extends Fragment {

    private FragmentDevocionalBinding binding;
    private DevocionalViewModel devocionalViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        // 1. Inicializar el ViewModel
        devocionalViewModel = new ViewModelProvider(this).get(DevocionalViewModel.class);

        binding = FragmentDevocionalBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // 2. Configurar el RecyclerView
        binding.rvEnsenanzas.setLayoutManager(new LinearLayoutManager(getContext()));

        // 3. Observar la lista de enseñanzas
        devocionalViewModel.getListaEnsenanzas().observe(getViewLifecycleOwner(), ensenanzas -> {
            if (ensenanzas != null) {
                EnsenanzaAdapter adapter = new EnsenanzaAdapter(ensenanzas, getContext());
                binding.rvEnsenanzas.setAdapter(adapter);
            }
        });

        // 4. Disparar la carga de datos desde la API
        devocionalViewModel.cargarEnsenanzas();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}