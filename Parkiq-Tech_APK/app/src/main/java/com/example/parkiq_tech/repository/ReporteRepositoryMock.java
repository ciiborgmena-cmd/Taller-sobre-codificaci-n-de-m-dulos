package com.example.parkiq_tech.repository;

import com.example.parkiq_tech.model.Espacio;
import com.example.parkiq_tech.model.EstadoEspacio;
import com.example.parkiq_tech.model.ReporteResumen;

import java.util.List;

public class ReporteRepositoryMock implements ReporteRepository {

    @Override
    public ReporteResumen obtenerResumenDelDia() {
        // Reutiliza el mismo repositorio de espacios para calcular
        // cuántos están libres/ocupados EN ESTE MOMENTO (dato real
        // de tu sesión de pruebas, no inventado).
        EspacioRepository espacioRepository = new EspacioRepositoryMock();
        List<Espacio> espacios = espacioRepository.obtenerEspacios();

        int libres = 0, ocupados = 0;
        for (Espacio e : espacios) {
            if (e.getEstado() == EstadoEspacio.LIBRE) libres++;
            else if (e.getEstado() == EstadoEspacio.OCUPADO) ocupados++;
        }

        // El resto de métricas son datos simulados fijos para esta
        // evidencia (en una versión conectada a MySQL, vendrían de
        // consultas SUM()/COUNT()/AVG() sobre Registro_entrada y Pago).
        int vehiculosAtendidos = 27;
        double ingresosTotales = 342000.0;
        double tiempoPromedioMinutos = 78.5;

        return new ReporteResumen(vehiculosAtendidos, ingresosTotales, libres, ocupados, tiempoPromedioMinutos);
    }
}
