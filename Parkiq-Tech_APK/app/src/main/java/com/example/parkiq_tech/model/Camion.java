package com.example.parkiq_tech.model;

public class Camion extends Vehiculo {
    private double capacidadCarga;
    private int ejes;

    public Camion(String placa, String marca, String modelo, String color, double capacidadCarga, int eje) {
        super(placa, marca, modelo, color);
        this.capacidadCarga = capacidadCarga;
        this.ejes = ejes;
    }
    @Override
    public String getTipo() {
        return "CAMION";
    }
}
