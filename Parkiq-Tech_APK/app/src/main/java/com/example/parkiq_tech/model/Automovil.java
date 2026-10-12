package com.example.parkiq_tech.model;

public class Automovil  extends Vehiculo {
    private int numeroPuertas;
    private String tipoCompustible;

    public Automovil(String placa, String marca, String modelo, String color, int numeroPuertas, String tipoCompustible) {
        super(placa, marca, modelo, color);
        this.numeroPuertas = numeroPuertas;
        this.tipoCompustible = tipoCompustible;
    }
    @Override
    public String getTipo() {
        return "AUTOMOVIL";
    }
}
