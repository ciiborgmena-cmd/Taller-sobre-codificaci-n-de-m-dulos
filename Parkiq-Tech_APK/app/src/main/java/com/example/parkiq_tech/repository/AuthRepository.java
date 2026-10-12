package com.example.parkiq_tech.repository;

import com.example.parkiq_tech.model.Usuario;



// PATRÓN REPOSITORY: esta interfaz define QUÉ operaciones existen
// para autenticación, sin decir CÓMO se obtienen los datos.
// La UI (LoginActivity) solo conocerá esta interfaz, nunca la
// implementación concreta — así, mañana podemos reemplazar
// AuthRepositoryMock por una versión real que llame a una API,
// sin tocar ni una línea de LoginActivity.
public interface AuthRepository {
    Usuario login(String usuario, String contrasena);
}




