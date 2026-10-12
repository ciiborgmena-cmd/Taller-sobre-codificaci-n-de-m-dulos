package com.example.parkiq_tech.repository;

import com.example.parkiq_tech.model.MetodoPago;
import com.example.parkiq_tech.model.Pago;

public interface PagoRepository {
    Pago procesarPago(double monto, MetodoPago metodo);
}
