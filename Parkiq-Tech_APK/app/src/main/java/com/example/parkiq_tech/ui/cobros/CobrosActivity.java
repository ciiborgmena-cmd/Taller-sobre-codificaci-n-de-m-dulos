package com.example.parkiq_tech.ui.cobros;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.parkiq_tech.R;
import com.example.parkiq_tech.model.MetodoPago;
import com.example.parkiq_tech.model.Pago;
import com.example.parkiq_tech.model.TarifaStrategy;
import com.example.parkiq_tech.model.TarifaStrategyFactory;
import com.example.parkiq_tech.repository.PagoRepository;
import com.example.parkiq_tech.repository.PagoRepositoryMock;

public class CobrosActivity extends AppCompatActivity {

    private TextView tvDetalleEspacio, tvTiempo, tvTotal, tvRecibo;
    private RadioGroup rgMetodoPago;
    private Button btnConfirmarPago;

    private PagoRepository pagoRepository;
    private double montoCalculado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cobros);

        tvDetalleEspacio = findViewById(R.id.tvDetalleEspacio);
        tvTiempo = findViewById(R.id.tvTiempo);
        tvTotal = findViewById(R.id.tvTotal);
        tvRecibo = findViewById(R.id.tvRecibo);
        rgMetodoPago = findViewById(R.id.rgMetodoPago);
        btnConfirmarPago = findViewById(R.id.btnConfirmarPago);

        pagoRepository = new PagoRepositoryMock();

        // Datos que llegarían desde el Dashboard al elegir un espacio
        // ocupado (por ahora los simulamos aquí con valores fijos,
        // para que puedas probar este módulo de forma independiente).
        int numeroEspacio = getIntent().getIntExtra("numeroEspacio", 2);
        String tipoVehiculo = getIntent().getStringExtra("tipoVehiculo");
        if (tipoVehiculo == null) tipoVehiculo = "AUTOMOVIL";
        long minutosEstacionado = getIntent().getLongExtra("minutosEstacionado", 95);

        tvDetalleEspacio.setText("Espacio #" + numeroEspacio + " - " + tipoVehiculo);
        tvTiempo.setText("Tiempo estacionado: " + minutosEstacionado + " minutos");

        // USO DEL PATRÓN STRATEGY: obtenemos la estrategia correcta
        // según el tipo de vehículo, sin necesidad de un if/else aquí.
        TarifaStrategy estrategia = TarifaStrategyFactory.obtenerEstrategia(tipoVehiculo);
        montoCalculado = estrategia.calcularCosto(minutosEstacionado);

        tvTotal.setText("Total a pagar: $" + String.format("%,.0f", montoCalculado));

        btnConfirmarPago.setOnClickListener(v -> confirmarPago());
    }

    private void confirmarPago() {
        MetodoPago metodoSeleccionado;
        int idSeleccionado = rgMetodoPago.getCheckedRadioButtonId();

        if (idSeleccionado == R.id.rbEfectivo) {
            metodoSeleccionado = MetodoPago.EFECTIVO;
        } else if (idSeleccionado == R.id.rbTarjeta) {
            metodoSeleccionado = MetodoPago.TARJETA;
        } else {
            metodoSeleccionado = MetodoPago.QR_NEQUI;
        }

        Pago pago = pagoRepository.procesarPago(montoCalculado, metodoSeleccionado);

        tvRecibo.setText(pago.generarRecibo());
        tvRecibo.setVisibility(View.VISIBLE);
        btnConfirmarPago.setEnabled(false); // evita pagar dos veces por error
    }
}
