package com.example.parkiq_tech;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import com.example.parkiq_tech.model.Automovil;
import com.example.parkiq_tech.model.Vehiculo;
import com.example.parkiq_tech.model.VehiculoFactory;

import org.junit.Test;

public class VehiculoFactoryTest {

    @Test
    public void crear_automovil_debeDevolverInstanciaCorrecta() {
        Vehiculo vehiculo = VehiculoFactory.crear(
                "AUTOMOVIL", "ABC-123", "Toyota", "Corolla", "Rojo");

        assertEquals(true, vehiculo instanceof Automovil);
        assertEquals("AUTOMOVIL", vehiculo.getTipo());
        assertEquals("ABC-123", vehiculo.getPlaca());
    }

    @Test
    public void crear_tipoInvalido_debeLanzarExcepcion() {
        // Verifica que el Factory rechace tipos de vehículo no soportados
        assertThrows(IllegalArgumentException.class, () -> {
            VehiculoFactory.crear(
                    "AVION", "XXX-000", "N/A", "N/A", "N/A");
        });
    }
}
