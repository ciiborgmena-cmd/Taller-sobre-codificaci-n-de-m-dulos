package com.example.parkiq_tech.model;

//Patron Strategy: define una interfaz comun para "calcular el costo", pero cada tipo de vehiculo la implementa distinto.
// Asi evitamos un gigantesco if/else de tipos dentro de una sola clase - cada estrategia vive su propio archivo.
public interface TarifaStrategy {
    double calcularCosto(long minutosEstacionado);
}
