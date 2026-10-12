package com.example.parkiq_tech.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.parkiq_tech.R;
import com.example.parkiq_tech.model.Usuario;
import com.example.parkiq_tech.repository.AuthRepository;
import com.example.parkiq_tech.repository.AuthRepositoryMock;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsuario, etContrasena;
    private TextView tvError;
    private Button btnLogin;

    // La Activity depende de la INTERFAZ, no de la clase concreta.
    // Esto es lo que hace posible cambiar la implementación después
    // sin tocar este archivo (principio de Inversión de Dependencias).
    private AuthRepository authRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsuario = findViewById(R.id.etUsuario);
        etContrasena = findViewById(R.id.etContrasena);
        tvError = findViewById(R.id.tvError);
        btnLogin = findViewById(R.id.btnLogin);

        // Por ahora instanciamos directamente la versión mock.
        // Más adelante esto podría venir inyectado (Dependency Injection).
        authRepository = new AuthRepositoryMock();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentarLogin();
            }
        });
    }

    private void intentarLogin() {
        String usuario = etUsuario.getText().toString().trim();
        String contrasena = etContrasena.getText().toString().trim();

        // Validación básica de campos vacíos, antes de consultar el repositorio
        if (usuario.isEmpty() || contrasena.isEmpty()) {
            mostrarError("Por favor completa usuario y contraseña.");
            return;
        }

        Usuario usuarioAutenticado = authRepository.login(usuario, contrasena);

        if (usuarioAutenticado != null) {
            tvError.setVisibility(View.GONE);
            // Login exitoso: navega al Dashboard (lo construimos en la Fase 3)
            // Intent: mecanismo de Android para pasar de una Activity a otra.
            Intent intent = new Intent(LoginActivity.this,
                    com.example.parkiq_tech.ui.dashboard.DashboardActivity.class);
            intent.putExtra("rol", usuarioAutenticado.getRol());
            intent.putExtra("nombre", usuarioAutenticado.getNombre());
            startActivity(intent);
            finish(); // cierra LoginActivity para que no se pueda volver con el botón "atrás"
        } else {
            mostrarError("Usuario o contraseña incorrectos.");
        }
    }

    private void mostrarError(String mensaje) {
        tvError.setText(mensaje);
        tvError.setVisibility(View.VISIBLE);
    }
}
