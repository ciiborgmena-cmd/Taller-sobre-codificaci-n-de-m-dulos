package com.example.parkiq_tech.repository;

import com.example.parkiq_tech.model.Reserva;
import java.util.List;

public interface ReservaRepository {
    Reserva crearReserva(Reserva reserva);
    void cancelarReserva(int idReserva);
    List<Reserva> obtenerReservasActivas();
    }
