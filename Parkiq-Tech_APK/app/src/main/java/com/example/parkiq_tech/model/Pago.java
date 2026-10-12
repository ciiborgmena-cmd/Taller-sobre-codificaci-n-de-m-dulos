package com.example.parkiq_tech.model;

import java.util.Date;
public class Pago {
    private int id;
    private Date fecha;
    private double monto;
    private MetodoPago metodoPago;
    private String estado; // "PENDIENTE", "COMPLETO"

    public Pago(int id, double monto, MetodoPago metodoPago) {
        this.id = id;
        this.fecha = new Date();
        this.monto = monto;
        this.metodoPago = metodoPago;
        this.estado = "PENDIENTE";
    }

    public void realizarPago() {
        this.estado = "COMPLETO";
    }

    public String generarRecibo() {
        return "Recibo #" + id + " | Monto: $" + monto + " | Método: " + metodoPago + " | Estado: " + estado;
    }

    public double getMonto() { return monto; }
    public String getEstado() { return estado; }
}