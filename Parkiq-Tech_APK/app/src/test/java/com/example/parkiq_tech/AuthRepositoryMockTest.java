package com.example.parkiq_tech;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import com.example.parkiq_tech.model.Usuario;
import com.example.parkiq_tech.repository.AuthRepository;
import com.example.parkiq_tech.repository.AuthRepositoryMock;

import org.junit.Before;
import org.junit.Test;

public class AuthRepositoryMockTest {

    private AuthRepository authRepository;

    // @Before: este método se ejecuta ANTES de cada @Test, para
    // partir siempre de un estado limpio y no depender del orden
    // en que JUnit decida correr las pruebas.
    @Before
    public void setUp() {
        authRepository = new AuthRepositoryMock();
    }

    @Test
    public void login_credencialesCorrectas_debeRetornarUsuario() {
        Usuario resultado = authRepository.login("admin", "1234");
        assertNotNull(resultado);
        assertEquals("ADMINISTRADOR", resultado.getRol());
    }

    @Test
    public void login_contrasenaIncorrecta_debeRetornarNull() {
        Usuario resultado = authRepository.login("admin", "clave_mala");
        assertNull(resultado);
    }

    @Test
    public void login_usuarioNoExiste_debeRetornarNull() {
        Usuario resultado = authRepository.login("usuario_fantasma", "1234");
        assertNull(resultado);
    }
}
