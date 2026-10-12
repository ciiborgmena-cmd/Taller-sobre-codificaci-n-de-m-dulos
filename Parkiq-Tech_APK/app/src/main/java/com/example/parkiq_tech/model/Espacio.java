package com.example.parkiq_tech.model;

public class Espacio {
    private int numero;
    private String tipo;        // ej. "Cubierto - normal"
    private EstadoEspacio estado;
    private String ubicacion;   // ej. "A - 04 - Zona A"

    public Espacio(int numero, String tipo, EstadoEspacio estado, String ubicacion) {
        this.numero = numero;
        this.tipo = tipo;
        this.estado = estado;
        this.ubicacion = ubicacion;
    }

    public void cambiarEstado(EstadoEspacio nuevoEstado) {
        this.estado = nuevoEstado;
    }

    // Verifica si este espacio es adecuado para un tipo de vehículo dado.
    public boolean esAdecuado(String tipoVehiculo) {
        return this.estado == EstadoEspacio.LIBRE;
    }

    public int getNumero() { return numero; }
    public String getUbicacion() { return ubicacion; }
    public EstadoEspacio getEstado() { return estado; }
}
