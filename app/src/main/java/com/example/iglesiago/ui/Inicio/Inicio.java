package com.example.iglesiago.ui.Inicio;
//package com.example.iglesiago.ui.Inicio;

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

public class Inicio extends Fragment {

    private FragmentInicioBinding binding;
    private InicioViewModel inicioViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        // 1. Inicializar el ViewModel
        inicioViewModel = new ViewModelProvider(this).get(InicioViewModel.class);

        binding = FragmentInicioBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // 2. Configurar el RecyclerView (LayoutManager es obligatorio)
        binding.rvNoticias.setLayoutManager(new LinearLayoutManager(getContext()));

        // 3. Observar la lista de noticias que viene de la API
        inicioViewModel.getListaNoticias().observe(getViewLifecycleOwner(), noticias -> {
            if (noticias != null) {
                // Creamos y seteamos el adaptador con las noticias reales
                NoticiaAdapter adapter = new NoticiaAdapter(noticias, getContext());
                binding.rvNoticias.setAdapter(adapter);
            }
        });

        // 4. Mandar a cargar las noticias desde el servidor
        inicioViewModel.cargarNoticias();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}