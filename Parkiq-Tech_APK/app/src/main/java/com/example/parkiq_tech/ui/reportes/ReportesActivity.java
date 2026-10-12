package com.example.parkiq_tech.ui.reportes;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.parkiq_tech.R;
import com.example.parkiq_tech.model.ReporteResumen;
import com.example.parkiq_tech.repository.ReporteRepository;
import com.example.parkiq_tech.repository.ReporteRepositoryMock;

public class ReportesActivity extends AppCompatActivity {

    private TextView tvVehiculosAtendidos, tvIngresos, tvEspacios, tvTiempoPromedio;
    private ReporteRepository reporteRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportes);

        tvVehiculosAtendidos = findViewById(R.id.tvVehiculosAtendidos);
        tvIngresos = findViewById(R.id.tvIngresos);
        tvEspacios = findViewById(R.id.tvEspacios);
        tvTiempoPromedio = findViewById(R.id.tvTiempoPromedio);

        reporteRepository = new ReporteRepositoryMock();
        ReporteResumen resumen = reporteRepository.obtenerResumenDelDia();

        tvVehiculosAtendidos.setText("Vehículos atendidos hoy: " + resumen.getVehiculosAtendidos());
        tvIngresos.setText("Ingresos totales: $" + String.format("%,.0f", resumen.getIngresosTotales()));
        tvEspacios.setText("Espacios libres: " + resumen.getEspaciosLibres() +
                " | Ocupados: " + resumen.getEspaciosOcupados());
        tvTiempoPromedio.setText("Tiempo promedio de estancia: " +
                String.format("%.1f", resumen.getTiempoPromedioMinutos()) + " minutos");
    }
}
