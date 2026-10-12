package com.example.parkiq_tech.model;

// Clase simple (a veces llamada DTO: Data Transfer Object) que
// agrupa varias métricas juntas para mostrarlas en la pantalla
// de Reportes, sin mezclar esta lógica dentro de otras clases.
public class ReporteResumen {
    private int vehiculosAtendidos;
    private double ingresosTotales;
    private int espaciosLibres;
    private int espaciosOcupados;
    private double tiempoPromedioMinutos;

    public ReporteResumen(int vehiculosAtendidos, double ingresosTotales,
                          int espaciosLibres, int espaciosOcupados,
                          double tiempoPromedioMinutos) {
        this.vehiculosAtendidos = vehiculosAtendidos;
        this.ingresosTotales = ingresosTotales;
        this.espaciosLibres = espaciosLibres;
        this.espaciosOcupados = espaciosOcupados;
        this.tiempoPromedioMinutos = tiempoPromedioMinutos;
    }

    public int getVehiculosAtendidos() { return vehiculosAtendidos; }
    public double getIngresosTotales() { return ingresosTotales; }
    public int getEspaciosLibres() { return espaciosLibres; }
    public int getEspaciosOcupados() { return espaciosOcupados; }
    public double getTiempoPromedioMinutos() { return tiempoPromedioMinutos; }
}
