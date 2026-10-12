package com.example.parkiq_tech.model;

public class TarifaMotocicleta implements TarifaStrategy{
    private static final double VALOR_HORA = 1500.0;
    @Override
    public double calcularCosto(long minutosEstacionado) {
        double horas = Math.ceil(minutosEstacionado / 60.0);
        return horas * VALOR_HORA;
    }
}
