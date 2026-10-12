package com.example.parkiq_tech.model;

//Patron factory: en vez de que el resto de la app haga "new Automovil(...)" o "new Motocicleta"(..)
// directamente (lo obliga a conocer todos los tipos en muchos lugares) aqui la decision de que clase concreta crear.
// Si mañana se agrega un nuevo tipo de vehiculo, solo se modifica esta clase, no el resto de la app.

public class VehiculoFactory {
    public  static Vehiculo crear(String tipo, String placa, String marca, String modelo, String color) {
        switch (tipo.toUpperCase()) {
            case "AUTOMOVIL":
                return new Automovil(placa, marca, modelo, color, 4, "Gasolina");
            case "MOTOCICLETA":
                return new Motocicleta(placa, marca, modelo, color, 150);
            case "CAMION":
                return new Camion(placa, marca, modelo, color, 1000.0, 2);

            default:
                throw new IllegalArgumentException("Tipo de vehuculo no soportado: " + tipo);


        }
    }
}
