package com.example.parkiq_tech.repository;

import com.example.parkiq_tech.model.Espacio;
import com.example.parkiq_tech.model.EstadoEspacio;
import java.util.List;

public interface EspacioRepository {
    List<Espacio> obtenerEspacios();
    void actualizarEstado(int numeroEspacio, EstadoEspacio nuevoEstado);
}
