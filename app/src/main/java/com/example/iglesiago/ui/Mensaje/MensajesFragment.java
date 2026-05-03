package com.example.iglesiago.ui.Mensaje;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.iglesiago.R;
import com.example.iglesiago.model.Contacto;
import com.example.iglesiago.request.ApiClient;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MensajesFragment extends Fragment {
    private RecyclerView recyclerView;
    private com.example.iglesiago.ui.Mensajes.MensajeAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_mensajes_list, container, false);

        recyclerView = root.findViewById(R.id.rvMensajesAdmin);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        obtenerDatos();
        return root;
    }

    private void obtenerDatos() {
        // Obtenés el token que guardaste en el Login (SharedPreferences) a través de ApiClient
        String token = ApiClient.leerToken(requireContext());

        // Corregido: Uso de getApiIglesiaGo() y getConsultas() según ApiClient e IglesiaGoService
        ApiClient.getApiIglesiaGo().getConsultas(token).enqueue(new Callback<List<Contacto>>() {
            @Override
            public void onResponse(Call<List<Contacto>> call, Response<List<Contacto>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    adapter = new com.example.iglesiago.ui.Mensajes.MensajeAdapter(response.body());
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(getContext(), "Error al cargar mensajes", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Contacto>> call, Throwable t) {
                Toast.makeText(getContext(), "Fallo de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
