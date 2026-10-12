package com.example.parkiq_tech.model;

// Esta clase decide QUÉ estrategia de cálculo usar, según el tipo de vehículo.
// Internamente usa las 3 estrategias que ya creamos en la Fase 1 (TarifaAutomovil, TarifaMotocicleta, TarifaCamion).
public class TarifaStrategyFactory {

    public static TarifaStrategy obtenerEstrategia(String tipoVehiculo) {
        switch (tipoVehiculo.toUpperCase()) {
            case "AUTOMOVIL":
                return new TarifaAutomovil();
            case "MOTOCICLETA":
                return new TarifaMotocicleta();
            case "CAMION":
                return new TarifaCamion();
            default:
                throw new IllegalArgumentException("Tipo de vehículo no soportado: " + tipoVehiculo);
        }
    }
}
