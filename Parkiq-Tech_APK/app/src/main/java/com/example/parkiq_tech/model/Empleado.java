package com.example.parkiq_tech.model;

import java.util.Date;

public class Empleado extends Usuario {
    private String numeroEmpleado;
    private Date fechaIngreso;
    public Empleado(int id, String nombre, String email, String telefono, String numeroEmpleado, Date fechaIngreso) {
        super(id, nombre, email, telefono);
        this.numeroEmpleado = numeroEmpleado;
        this.fechaIngreso = fechaIngreso;
    }
    public void registrarEntrada(Vehiculo vehiculo, Espacio espacio) {
        espacio.cambiarEstado(EstadoEspacio. OCUPADO);
    }
    public void registarSalida(Espacio espacio) {
        espacio.cambiarEstado(EstadoEspacio.LIBRE);
    }
    public String getNumeroEmpleado() {
        return numeroEmpleado;
    }
     @Override
    public String getRol(){
        return "EMPLEADO";
     }

}
