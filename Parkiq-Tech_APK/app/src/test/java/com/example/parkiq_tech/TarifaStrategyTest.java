package com.example.parkiq_tech;

import static org.junit.Assert.assertEquals;

import com.example.parkiq_tech.model.TarifaAutomovil;
import com.example.parkiq_tech.model.TarifaMotocicleta;
import com.example.parkiq_tech.model.TarifaStrategy;
import com.example.parkiq_tech.model.TarifaStrategyFactory;

import org.junit.Test;

// Pruebas unitarias sobre el patrón Strategy: verificamos que el
// cálculo de tarifas sea el esperado para distintos escenarios.
public class TarifaStrategyTest {

    @Test
    public void calcularCosto_automovil_1hora_debeSerTarifaBase() {
        TarifaStrategy tarifa = new TarifaAutomovil();
        double resultado = tarifa.calcularCosto(60); // exactamente 60 min = 1 hora
        assertEquals(3000.0, resultado, 0.01);
    }

    @Test
    public void calcularCosto_automovil_95minutos_debeRedondearA2horas() {
        // 95 minutos = 1.58 horas -> debe redondear HACIA ARRIBA a 2 horas
        TarifaStrategy tarifa = new TarifaAutomovil();
        double resultado = tarifa.calcularCosto(95);
        assertEquals(6000.0, resultado, 0.01);
    }

    @Test
    public void calcularCosto_motocicleta_30minutos_debeCobrar1hora() {
        // Incluso con solo 30 minutos, se redondea a 1 hora completa
        TarifaStrategy tarifa = new TarifaMotocicleta();
        double resultado = tarifa.calcularCosto(30);
        assertEquals(1500.0, resultado, 0.01);
    }

    @Test
    public void factory_debeDevolverEstrategiaCorrectaSegunTipo() {
        // Verifica que el Factory de estrategias entregue la clase correcta
        TarifaStrategy estrategia = TarifaStrategyFactory.obtenerEstrategia("MOTOCICLETA");
        assertEquals(true, estrategia instanceof TarifaMotocicleta);
    }
}
