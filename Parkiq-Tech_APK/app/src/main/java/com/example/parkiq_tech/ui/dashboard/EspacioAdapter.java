package com.example.parkiq_tech.ui.dashboard;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parkiq_tech.R;
import com.example.parkiq_tech.model.Espacio;
import com.example.parkiq_tech.model.EstadoEspacio;

import java.util.List;

// El Adapter conecta la lista de objetos Espacio (datos) con las
// vistas visuales de cada item (item_espacio.xml). RecyclerView
// va llamando a estos métodos automáticamente conforme el usuario
// hace scroll, reutilizando vistas para que sea eficiente.
public class EspacioAdapter extends RecyclerView.Adapter<EspacioAdapter.EspacioViewHolder> {

    private List<Espacio> espacios;
    private OnEspacioClickListener listener;

    // Interfaz para "avisar" a la Activity cuando el usuario toca un espacio.
    public interface OnEspacioClickListener {
        void onEspacioClick(Espacio espacio);
    }

    public EspacioAdapter(List<Espacio> espacios, OnEspacioClickListener listener) {
        this.espacios = espacios;
        this.listener = listener;
    }

    // ViewHolder: guarda las referencias a las vistas de UN item,
    // para no tener que buscarlas con findViewById cada vez (más rápido).
    static class EspacioViewHolder extends RecyclerView.ViewHolder {
        LinearLayout contenedor;
        TextView tvNumero, tvUbicacion;

        EspacioViewHolder(View itemView) {
            super(itemView);
            contenedor = itemView.findViewById(R.id.contenedorEspacio);
            tvNumero = itemView.findViewById(R.id.tvNumeroEspacio);
            tvUbicacion = itemView.findViewById(R.id.tvUbicacionEspacio);
        }
    }

    @NonNull
    @Override
    public EspacioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_espacio, parent, false);
        return new EspacioViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull EspacioViewHolder holder, int position) {
        Espacio espacio = espacios.get(position);

        holder.tvNumero.setText("#" + espacio.getNumero());
        holder.tvUbicacion.setText(espacio.getUbicacion());

        // Pinta el color según el estado (igual a tu maqueta de Dashboard)
        int color;
        switch (espacio.getEstado()) {
            case LIBRE:
                color = Color.parseColor("#4CAF50"); // verde
                break;
            case OCUPADO:
                color = Color.parseColor("#F44336"); // rojo
                break;
            case RESERVADO:
                color = Color.parseColor("#FF9800"); // naranja
                break;
            case DISCAPACITADOS:
                color = Color.parseColor("#2196F3"); // azul
                break;
            default:
                color = Color.GRAY;
        }
        holder.contenedor.setBackgroundColor(color);

        // Cuando el usuario toca este espacio, se avisa a la Activity.
        holder.itemView.setOnClickListener(v -> listener.onEspacioClick(espacio));
    }

    @Override
    public int getItemCount() {
        return espacios.size();
    }
}
