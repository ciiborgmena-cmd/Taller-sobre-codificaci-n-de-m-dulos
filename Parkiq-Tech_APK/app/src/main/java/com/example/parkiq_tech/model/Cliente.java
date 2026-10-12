package com.example.parkiq_tech.model;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Usuario {

    // Un cliente puede tener varios vehiculos registrados.
    private List<Vehiculo> vehiculosRegistrados;

    public Cliente(int id, String nombre, String email, String telefono) {
        super(id, nombre, email, telefono); // Llama al constructor de usuario
        this.vehiculosRegistrados = new ArrayList<>();

    }
    public void registarVehiculo(Vehiculo vehiculo) {

        vehiculosRegistrados.add(vehiculo);
    }

    public List<Vehiculo> consultarHistorial() {

        return vehiculosRegistrados;
    }
    @Override
    public String getRol() {
        return "CLIENTE";
    }
}
