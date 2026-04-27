package com.example.iglesiago.ui.IniciarSesion;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.iglesiago.databinding.FragmentIniciarSesionBinding;

public class IniciarSesionFragment extends Fragment {

    private FragmentIniciarSesionBinding binding;
    private IniciarSesionViewModel iniciarSesionViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        // Inicializamos el ViewModel
        iniciarSesionViewModel = new ViewModelProvider(this).get(IniciarSesionViewModel.class);

        binding = FragmentIniciarSesionBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Configuramos el click del botón "Ingresar"
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.etEmail.getText().toString();
                String password = binding.etPassword.getText().toString();

                if (!email.isEmpty() && !password.isEmpty()) {
                    // Llamamos al método de login en el ViewModel
                    iniciarSesionViewModel.login(email, password);
                } else {
                    Toast.makeText(getContext(), "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Observamos el resultado del login (por ejemplo, si hubo un error)
        iniciarSesionViewModel.getError().observe(getViewLifecycleOwner(), mensaje -> {
            if (mensaje != null) {
                Toast.makeText(getContext(), mensaje, Toast.LENGTH_LONG).show();
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}