package com.example.parkiq_tech.repository;

import com.example.parkiq_tech.model.Administrador;
import com.example.parkiq_tech.model.Cliente;
import com.example.parkiq_tech.model.Empleado;
import com.example.parkiq_tech.model.Usuario;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

// Implementacion "mock": simula una base de datos con un HashMap en memoria
// Cumple el mismo contrato (AuthRepository) que cumpliria una implementacion real conectada a MySQL.

public class AuthRepositoryMock implements AuthRepository {

    // Simula la tabla Usuario: llave = nombre de usuario, valor = contraseña
    private Map<String, String> credenciales;
    private Map<String, Usuario> usuarios;

    public AuthRepositoryMock() {
        credenciales = new HashMap<>();
        usuarios = new HashMap<>();

        // Datos de prueba (equivalentes a un INSERT en tu tabla Usuario)
        credenciales.put("admin", "1234");
        usuarios.put("admin", new Administrador(1, "Ana Torres",
                "ana@parkiqtech.com", "3001234567", 1));

        credenciales.put("empleado1", "1234");
        usuarios.put("empleado1", new Empleado(2, "Carlos Ruiz",
                "carlos@parkiqtech.com", "3007654321", "EMP-001", new Date()));

        credenciales.put("cliente1", "1234");
        usuarios.put("cliente1", new Cliente(3, "Juan Pérez",
                "juan@example.com", "3009876543"));
    }

    @Override
    public Usuario login(String usuario, String contrasena) {
        // Verifica que el usuario exista Y que la contraseña coincida
        if (credenciales.containsKey(usuario) && credenciales.get(usuario).equals(contrasena)) {
            return usuarios.get(usuario);
        }
        return null; // null = credenciales inválidas
    }
}