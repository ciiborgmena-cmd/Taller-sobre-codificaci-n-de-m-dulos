package com.example.parkiq_tech.model;

import java.util.Date;

public class Reserva {
    private int id;
    private Cliente cliente;
    private Vehiculo vehiculo;
    private Espacio espacio;
    private Date fechaHoraEntrada;
    private Date fechaHoraSalida;
    private String estado; // "PENDIENTE", "ACTIVA", "FINALIZADA", "CANCELADA"
    private double tarifaTotal;

    public Reserva(int id, Cliente cliente, Vehiculo vehiculo, Espacio espacio) {
        this.id = id;
        this.cliente = cliente;
        this.vehiculo = vehiculo;
        this.espacio = espacio;
        this.fechaHoraEntrada = new Date();
        this.estado = "PENDIENTE";
    }

    public void cancelarReserva() {
        this.estado = "CANCELADA";
        espacio.cambiarEstado(EstadoEspacio.LIBRE);
    }

    // Aquí es donde se USA el patrón Strategy: recibe la
    // estrategia adecuada según el tipo de vehículo y calcula.
    public double calcularCosto(TarifaStrategy estrategia, long minutosEstacionado) {
        this.tarifaTotal = estrategia.calcularCosto(minutosEstacionado);
        return this.tarifaTotal;
    }

    public int getId() { return id; }
    public Espacio getEspacio() { return espacio; }
    public double getTarifaTotal() { return tarifaTotal; }
    public String getEstado() { return estado; }
}