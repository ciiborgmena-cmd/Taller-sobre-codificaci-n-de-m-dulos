package com.example.parkiq_tech.repository;

import com.example.parkiq_tech.model.Reserva;

import java.util.ArrayList;
import java.util.List;

public class ReservaRepositoryMock implements ReservaRepository {

    private static List<Reserva> reservas = new ArrayList<>();
    private static int siguienteId = 1;

    @Override
    public Reserva crearReserva(Reserva reserva) {
        // Como Reserva ya trae su id en 0 desde la Activity, se lo asignamos aquí (simulando lo que haría un AUTO_INCREMENT
        // en la base de datos real).
        reservas.add(reserva);
        return reserva;
    }

    @Override
    public void cancelarReserva(int idReserva) {
        for (Reserva r : reservas) {
            if (r.getId() == idReserva) {
                r.cancelarReserva();
                break;
            }
        }
    }

    @Override
    public List<Reserva> obtenerReservasActivas() {
        List<Reserva> activas = new ArrayList<>();
        for (Reserva r : reservas) {
            if (r.getEstado().equals("PENDIENTE") || r.getEstado().equals("ACTIVA")) {
                activas.add(r);
            }
        }
        return activas;
    }

    public static int generarSiguienteId() {
        return siguienteId++;
    }
}
