package com.example.parkiq_tech.repository;

import com.example.parkiq_tech.model.MetodoPago;
import com.example.parkiq_tech.model.Pago;

import java.util.ArrayList;
import java.util.List;

public class PagoRepositoryMock implements PagoRepository {

    // Simula la tabla Pago: guarda un historial en memoria mientras dure la sesión de la app.

    private static List<Pago> pagosRealizados = new ArrayList<>();
    private static int siguienteId = 1;

    @Override
    public Pago procesarPago(double monto, MetodoPago metodo) {
        Pago pago = new Pago(siguienteId++, monto, metodo);
        pago.realizarPago(); // marca el pago como "COMPLETO"
        pagosRealizados.add(pago);
        return pago;
    }

    public List<Pago> obtenerHistorial() {
        return pagosRealizados;
    }
}
