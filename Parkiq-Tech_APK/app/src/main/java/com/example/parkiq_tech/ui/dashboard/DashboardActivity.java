package com.example.parkiq_tech.ui.dashboard;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.parkiq_tech.R;
import com.example.parkiq_tech.model.Espacio;
import com.example.parkiq_tech.model.EstadoEspacio;
import com.example.parkiq_tech.repository.EspacioRepository;
import com.example.parkiq_tech.repository.EspacioRepositoryMock;


public class DashboardActivity extends AppCompatActivity {

    private RecyclerView rvEspacios;
    private EspacioRepository espacioRepository;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Recupera los datos que envió LoginActivity con putExtra()
        String nombre = getIntent().getStringExtra("nombre");
        String rol = getIntent().getStringExtra("rol");

        TextView tvBienvenida = findViewById(R.id.tvBienvenida);
        tvBienvenida.setText("Hola, " + nombre + " (" + rol + ")");

        espacioRepository = new EspacioRepositoryMock();

        rvEspacios = findViewById(R.id.rvEspacios);
        // GridLayoutManager con 3 columnas: organiza el RecyclerView
        // como una cuadrícula en vez de una lista vertical simple.
        rvEspacios.setLayoutManager(new GridLayoutManager(this, 3));

        EspacioAdapter adapter = new EspacioAdapter(
                espacioRepository.obtenerEspacios(),
                espacio -> manejarClickEspacio(espacio) // lambda: implementación rápida del listener
        );
        rvEspacios.setAdapter(adapter);

        Button btnVerReportes = findViewById(R.id.btnVerReportes);
        btnVerReportes.setOnClickListener(v -> {
            startActivity(new Intent(this, com.example.parkiq_tech.ui.reportes.ReportesActivity.class));
        });
    }

    private void manejarClickEspacio(Espacio espacio) {
        Intent intent;
        if (espacio.getEstado() == EstadoEspacio.LIBRE) {
            // Espacio libre → ir a crear una Reserva
            intent = new Intent(this, com.example.parkiq_tech.ui.reservas.ReservaActivity.class);
            intent.putExtra("numeroEspacio", espacio.getNumero());
        } else if (espacio.getEstado() == EstadoEspacio.OCUPADO) {
            // Espacio ocupado → ir a Cobros a liquidar la salida
            intent = new Intent(this, com.example.parkiq_tech.ui.cobros.CobrosActivity.class);
            intent.putExtra("numeroEspacio", espacio.getNumero());
            intent.putExtra("tipoVehiculo", "AUTOMOVIL"); // valor de ejemplo por ahora
            intent.putExtra("minutosEstacionado", 95L);
        } else {
            Toast.makeText(this, "Espacio " + espacio.getNumero() + " - Estado: " + espacio.getEstado(), Toast.LENGTH_SHORT).show();
            return;
        }
        startActivity(intent);
    }
}
