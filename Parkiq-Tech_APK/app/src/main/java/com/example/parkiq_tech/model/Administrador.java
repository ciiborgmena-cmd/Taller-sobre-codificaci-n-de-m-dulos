package com.example.parkiq_tech.model;

public class Administrador extends Usuario{
    private int nivelAcceso;

    public Administrador( int id, String nombre, String email, String telefono, int nivelAcceso) {
        super( id, nombre, email, telefono);
        this.nivelAcceso = nivelAcceso;
    }
    public int getNivelAcceso() {
        return nivelAcceso;
    }
    @Override
    public String getRol(){
        return "ADMINISTRADOR";
    }
}
