package com.example.parkiq_tech;

import static org.junit.Assert.assertEquals;

import com.example.parkiq_tech.model.EstadoEspacio;
import com.example.parkiq_tech.repository.EspacioRepository;
import com.example.parkiq_tech.repository.EspacioRepositoryMock;

import org.junit.Test;

public class EspacioRepositoryMockTest {

    @Test
    public void actualizarEstado_debeCambiarElEstadoCorrectamente() {
        EspacioRepository repo = new EspacioRepositoryMock();

        repo.actualizarEstado(1, EstadoEspacio.OCUPADO);

        EstadoEspacio estadoActual = repo.obtenerEspacios().stream()
                .filter(e -> e.getNumero() == 1)
                .findFirst()
                .get()
                .getEstado();

        assertEquals(EstadoEspacio.OCUPADO, estadoActual);
    }
}
