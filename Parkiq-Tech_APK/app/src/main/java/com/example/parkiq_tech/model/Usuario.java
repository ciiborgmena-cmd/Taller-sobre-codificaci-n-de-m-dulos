package com.example.parkiq_tech.model;

// Clase abstracta: no se puede instanciar directamente ("new usuario()" daria error).
// Solo sirve como base para que Cliente, Empleado y Administrador hereden sus atrivutos y metodos comunes
// Esto es herencia uno de los pilares de POO
public abstract class Usuario {

    // Atributos privados (encapsulamineto): nadien fuera de esta clase puede modificarlos directamente,
    // solo atravez de los metodos get/set publicos.
    private int id;
    private String nombre;
    private String email;
    private String telefono;

    public Usuario(int id, String nombre, String email, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
    }

    // Metodo comun a todos los tipos de usuario.
    public void actualizarDatos(String nuevoNombre, String nuevoTelefono) {
        this.nombre = nuevoNombre;
        this.telefono = nuevoTelefono;
    }
    // Getters y setters (accesos controlados a los atributos privados)
    public int getId(){
        return id;
    }

    public String getNombre() {
        return nombre;
    }
    public String getEmail() {
        return email;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    // Metodo abstracto: Obliga a que cada subclase (Cliente, Empleado, Administrador)
    // Defina su priopia versiuon de "rol". Esto es polimorfosismo: cadas subclase responde
    // Distinto a la misma pregunta.
    public abstract String getRol();
}
