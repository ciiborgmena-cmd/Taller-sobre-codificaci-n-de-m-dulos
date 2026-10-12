package com.example.parkiq_tech.model;

public class Motocicleta extends Vehiculo {
    private int cilindraje;
    public Motocicleta(String placa, String marca, String modelo, String color, int cilindraje) {
        super(placa, marca, modelo, color);
        this.cilindraje = cilindraje;
    }
    @Override
    public String getTipo() {
        return "MOTOCICLETA";
    }
}
