package com.example.iglesiago.ui.IniciarSesion;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.iglesiago.R;
import com.example.iglesiago.databinding.FragmentIniciarSesionBinding;

public class IniciarSesionFragment extends Fragment {

    private FragmentIniciarSesionBinding binding;
    private IniciarSesionViewModel iniciarSesionViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        iniciarSesionViewModel = new ViewModelProvider(this).get(IniciarSesionViewModel.class);

        binding = FragmentIniciarSesionBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // 1. Click del botón "Ingresar"
        binding.btnLogin.setOnClickListener(v -> {
            String email = binding.etEmail.getText().toString().trim();
            String password = binding.etPassword.getText().toString().trim();

            if (!email.isEmpty() && !password.isEmpty()) {
                binding.pbLoading.setVisibility(View.VISIBLE);
                binding.btnLogin.setEnabled(false);
                iniciarSesionViewModel.login(email, password);
            } else {
                Toast.makeText(getContext(), "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
            }
        });

        // 2. Observar Éxito (Navegación)
        iniciarSesionViewModel.getLoginExitoso().observe(getViewLifecycleOwner(), exitoso -> {
            if (exitoso != null && exitoso) {
                binding.pbLoading.setVisibility(View.GONE);
                // IMPORTANTE: Asegurate que 'nav_inicio' sea el ID correcto en tu nav_graph.xml
                Navigation.findNavController(requireView()).navigate(R.id.nav_inicio);
            }
        });

        // 3. Observar Errores
        iniciarSesionViewModel.getError().observe(getViewLifecycleOwner(), mensaje -> {
            if (mensaje != null) {
                binding.pbLoading.setVisibility(View.GONE);
                binding.btnLogin.setEnabled(true);
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