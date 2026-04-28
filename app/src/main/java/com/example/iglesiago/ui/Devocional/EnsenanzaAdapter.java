package com.example.iglesiago.ui.Devocional;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.iglesiago.R;
import com.example.iglesiago.model.Ensenanza;
import java.util.List;

public class EnsenanzaAdapter extends RecyclerView.Adapter<EnsenanzaAdapter.ViewHolder> {
    private List<Ensenanza> lista;
    private Context context;

    public EnsenanzaAdapter(List<Ensenanza> lista, Context context) {
        this.lista = lista;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ensenanza, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Ensenanza e = lista.get(position);
        holder.tvTitulo.setText(e.getTitulo());
        holder.tvContenido.setText(e.getContenido());

        // Truco para la miniatura de YouTube
        if (e.getVideoUrl() != null && e.getVideoUrl().contains("embed/")) {
            String[] parts = e.getVideoUrl().split("embed/");
            if (parts.length > 1) {
                String videoId = parts[1];
                String thumbUrl = "https://img.youtube.com/vi/" + videoId + "/0.jpg";

                Glide.with(context)
                        .load(thumbUrl)
                        .placeholder(R.drawable.ic_launcher_background)
                        .into(holder.imgVideo);
            }
        }
    }

    @Override
    public int getItemCount() { return lista.size(); }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitulo, tvContenido;
        ImageView imgVideo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitulo = itemView.findViewById(R.id.tvTituloEnsenanza);
            tvContenido = itemView.findViewById(R.id.tvContenidoEnsenanza);
            imgVideo = itemView.findViewById(R.id.imgVideoEnsenanza);
        }
    }
}