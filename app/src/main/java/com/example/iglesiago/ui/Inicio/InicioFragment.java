package com.example.iglesiago.ui.Inicio;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.iglesiago.databinding.FragmentInicioBinding;

public class InicioFragment extends Fragment {

    private FragmentInicioBinding binding;
    private InicioViewModel inicioViewModel;
    private NoticiaAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        // Inicializamos el ViewModel
        inicioViewModel = new ViewModelProvider(this).get(InicioViewModel.class);

        binding = FragmentInicioBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // 1. Configurar el RecyclerView
        binding.rvNoticias.setLayoutManager(new LinearLayoutManager(getContext()));

        // 2. Observar los cambios en la lista de noticias
        inicioViewModel.getListaNoticias().observe(getViewLifecycleOwner(), noticias -> {
            if (noticias != null) {
                // Creamos el adaptador con la lista que llega de la API
                adapter = new NoticiaAdapter(noticias, getContext());
                binding.rvNoticias.setAdapter(adapter);
            }
        });

        // 3. Cargar las noticias al iniciar
        inicioViewModel.cargarNoticias();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}