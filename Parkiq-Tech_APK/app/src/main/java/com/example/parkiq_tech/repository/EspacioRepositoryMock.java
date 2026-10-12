package com.example.parkiq_tech.repository;

import com.example.parkiq_tech.model.Espacio;
import com.example.parkiq_tech.model.EstadoEspacio;

import java.util.ArrayList;
import java.util.List;

public class EspacioRepositoryMock implements EspacioRepository {

    // "static": se comparte entre todas las instancias de esta clase.
    // Esto simula una tabla de base de datos persistente durante toda
    // la sesión de la app (mientras no la cierres, los cambios de
    // estado se mantienen al navegar entre pantallas).
    private static List<Espacio> espacios = crearDatosIniciales();

    private static List<Espacio> crearDatosIniciales() {
        List<Espacio> lista = new ArrayList<>();
        // Zona A: espacios normales
        lista.add(new Espacio(1, "Cubierto - normal", EstadoEspacio.LIBRE, "A-01"));
        lista.add(new Espacio(2, "Cubierto - normal", EstadoEspacio.OCUPADO, "A-02"));
        lista.add(new Espacio(3, "Cubierto - normal", EstadoEspacio.LIBRE, "A-03"));
        lista.add(new Espacio(4, "Cubierto - normal", EstadoEspacio.RESERVADO, "A-04"));
        lista.add(new Espacio(5, "Cubierto - discapacitados", EstadoEspacio.DISCAPACITADOS, "A-05"));
        // Zona B: motos
        lista.add(new Espacio(6, "Descubierto - moto", EstadoEspacio.LIBRE, "B-01"));
        lista.add(new Espacio(7, "Descubierto - moto", EstadoEspacio.OCUPADO, "B-02"));
        lista.add(new Espacio(8, "Descubierto - moto", EstadoEspacio.LIBRE, "B-03"));
        // Zona C: camiones
        lista.add(new Espacio(9, "Descubierto - camión", EstadoEspacio.LIBRE, "C-01"));
        lista.add(new Espacio(10, "Descubierto - camión", EstadoEspacio.OCUPADO, "C-02"));
        return lista;
    }

    @Override
    public List<Espacio> obtenerEspacios() {
        return espacios;
    }

    @Override
    public void actualizarEstado(int numeroEspacio, EstadoEspacio nuevoEstado) {
        for (Espacio e : espacios) {
            if (e.getNumero() == numeroEspacio) {
                e.cambiarEstado(nuevoEstado);
                break;
            }
        }
    }
}
