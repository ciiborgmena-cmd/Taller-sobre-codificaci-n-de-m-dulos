package com.example.parkiq_tech.model;

public class TarifaAutomovil implements TarifaStrategy {
    private static final double VALOR_HORA = 3000.0;

    @Override
    public double calcularCosto(long minutosEstacionado) {
        double horas = Math.ceil(minutosEstacionado / 60.0); // redondea hacia arriba
        return horas * VALOR_HORA;
    }
}
