package com.example.parkiq_tech.model;

// Clase abstracta base para todos los tipos de vehiculo.
public abstract class Vehiculo {
    private String placa;
    private String marca;
    private String modelo;
    private String color;

    public Vehiculo(String placa, String marca, String modelo, String color) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
    }
    public String getPlaca() {
        return placa;
    }
    public String getMarca() {
        return marca;
    }
    public String getModelo() {
        return modelo;
    }
    // Cada subtipo debe decir que tipo de vehiculo es;
    // esto se usa para elegir la tarifa correcta (Strategy).
    public abstract String getTipo();
}
