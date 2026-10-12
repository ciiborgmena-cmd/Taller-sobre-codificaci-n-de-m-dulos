package com.example.parkiq_tech.ui.reservas;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.parkiq_tech.R;
import com.example.parkiq_tech.model.Cliente;
import com.example.parkiq_tech.model.Espacio;
import com.example.parkiq_tech.model.EstadoEspacio;
import com.example.parkiq_tech.model.Reserva;
import com.example.parkiq_tech.model.Vehiculo;
import com.example.parkiq_tech.model.VehiculoFactory;
import com.example.parkiq_tech.repository.EspacioRepository;
import com.example.parkiq_tech.repository.EspacioRepositoryMock;
import com.example.parkiq_tech.repository.ReservaRepository;
import com.example.parkiq_tech.repository.ReservaRepositoryMock;

public class ReservaActivity extends AppCompatActivity {

    private TextView tvEspacioSeleccionado, tvConfirmacion;
    private EditText etPlaca;
    private RadioGroup rgTipoVehiculo;
    private Button btnConfirmarReserva;

    private ReservaRepository reservaRepository;
    private EspacioRepository espacioRepository;
    private int numeroEspacio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva);

        tvEspacioSeleccionado = findViewById(R.id.tvEspacioSeleccionado);
        tvConfirmacion = findViewById(R.id.tvConfirmacion);
        etPlaca = findViewById(R.id.etPlaca);
        rgTipoVehiculo = findViewById(R.id.rgTipoVehiculo);
        btnConfirmarReserva = findViewById(R.id.btnConfirmarReserva);

        reservaRepository = new ReservaRepositoryMock();
        espacioRepository = new EspacioRepositoryMock();

        // El número de espacio llega desde el Dashboard, cuando el
        // usuario toca un espacio LIBRE (esto lo conectamos en el
        // siguiente paso, modificando DashboardActivity).
        numeroEspacio = getIntent().getIntExtra("numeroEspacio", 1);
        tvEspacioSeleccionado.setText("Espacio seleccionado: #" + numeroEspacio);

        btnConfirmarReserva.setOnClickListener(v -> confirmarReserva());
    }

    private void confirmarReserva() {
        String placa = etPlaca.getText().toString().trim();

        if (placa.isEmpty()) {
            etPlaca.setError("Ingresa la placa del vehículo");
            return;
        }

        // Determina el tipo de vehículo elegido
        String tipoVehiculo;
        int idSeleccionado = rgTipoVehiculo.getCheckedRadioButtonId();
        if (idSeleccionado == R.id.rbAutomovil) {
            tipoVehiculo = "AUTOMOVIL";
        } else if (idSeleccionado == R.id.rbMotocicleta) {
            tipoVehiculo = "MOTOCICLETA";
        } else {
            tipoVehiculo = "CAMION";
        }

        // USO DEL PATRÓN FACTORY: creamos el vehículo correcto sin
        // usar "new" directamente aquí.
        Vehiculo vehiculo = VehiculoFactory.crear(tipoVehiculo, placa, "N/D", "N/D", "N/D");

        // Cliente de prueba (en un flujo real vendría del login)
        Cliente cliente = new Cliente(99, "Cliente Invitado", "invitado@parkiqtech.com", "3000000000");

        // Obtiene el objeto Espacio real desde el repositorio
        Espacio espacio = null;
        for (Espacio e : espacioRepository.obtenerEspacios()) {
            if (e.getNumero() == numeroEspacio) {
                espacio = e;
                break;
            }
        }

        if (espacio == null || !espacio.esAdecuado(tipoVehiculo)) {
            tvConfirmacion.setText("Este espacio ya no está disponible.");
            tvConfirmacion.setVisibility(View.VISIBLE);
            return;
        }

        Reserva reserva = new Reserva(ReservaRepositoryMock.generarSiguienteId(), cliente, vehiculo, espacio);
        reservaRepository.crearReserva(reserva);

        // Actualiza el estado del espacio en el repositorio compartido
        espacioRepository.actualizarEstado(numeroEspacio, EstadoEspacio.RESERVADO);

        tvConfirmacion.setText("¡Reserva #" + reserva.getId() + " confirmada para el espacio #" + numeroEspacio + "!");
        tvConfirmacion.setVisibility(View.VISIBLE);
        btnConfirmarReserva.setEnabled(false);
    }
}