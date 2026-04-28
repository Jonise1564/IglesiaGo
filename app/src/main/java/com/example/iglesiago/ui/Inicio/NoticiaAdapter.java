package com.example.iglesiago.ui.Inicio;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.iglesiago.R;
import com.example.iglesiago.model.Noticia;

import java.util.List;

public class NoticiaAdapter extends RecyclerView.Adapter<NoticiaAdapter.ViewHolder> {

    private List<Noticia> noticias;
    private Context context;
    private LayoutInflater inflater;

    public NoticiaAdapter(List<Noticia> noticias, Context context) {
        this.noticias = noticias;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_noticia, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Noticia noticia = noticias.get(position);

        // 1. Usamos el título (si tu modelo ahora lo saca del 'cuerpo' o 'titulo')
        holder.tvTitulo.setText(noticia.getTitulo());

        // 2. Seteamos el cuerpo/contenido
        holder.tvContenido.setText(noticia.getContenido());

        // 3. CARGA DE IMAGEN:
        // Como el JSON ya trae "https://...", Glide lo carga directamente.
        String urlImagen = noticia.getImagenUrl();

        Glide.with(context)
                .load(urlImagen)
                .placeholder(R.drawable.ic_launcher_background)
                .error(android.R.drawable.stat_notify_error) // Ícono de error si la URL falla
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imgNoticia);
    }

    @Override
    public int getItemCount() {
        return noticias != null ? noticias.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitulo, tvContenido;
        ImageView imgNoticia;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            // Asegurate que este ID coincida con tu item_noticia.xml
            tvContenido = itemView.findViewById(R.id.tvDescripcion);
            imgNoticia = itemView.findViewById(R.id.imgNoticia);
        }
    }
}