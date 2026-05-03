package com.example.iglesiago.ui.Mensajes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.iglesiago.R;
import com.example.iglesiago.model.Contacto;
import java.util.List;

public class MensajeAdapter extends RecyclerView.Adapter<MensajeAdapter.MyViewHolder> {
    private List<Contacto> lista;

    public MensajeAdapter(List<Contacto> lista) { this.lista = lista; }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mensaje_card, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Contacto c = lista.get(position);
        holder.nombre.setText(c.getNombre());
        holder.correo.setText(c.getMail());
        holder.texto.setText(c.getMensaje());
    }

    @Override
    public int getItemCount() { return lista.size(); }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nombre, correo, texto;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.tvNombreItem);
            correo = itemView.findViewById(R.id.tvCorreoItem);
            texto = itemView.findViewById(R.id.tvMensajeItem);
        }
    }
}