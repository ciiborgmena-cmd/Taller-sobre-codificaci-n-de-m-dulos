package com.example.parkiq_tech;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

// MainActivity extiende de AppCompatActivity, la clase base
// que da soporte a las actividades de Android (ciclo de vida,
// compatibilidad con versiones anteriores, etc.)
public class MainActivity extends AppCompatActivity {

    // onCreate() se ejecuta automáticamente cuando el sistema
    // crea la actividad; aquí se inicializa la interfaz.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enlaza esta actividad con el diseño visual
        // definido en res/layout/activity_main.xml
        setContentView(R.layout.activity_login);

        // Busca el TextView por su id y lo guarda en una
        // variable para poder modificarlo desde el código.
        TextView tvMensaje = findViewById(R.id.tvError);

        // Cambia el texto que se muestra en pantalla.
        // Este es el mensaje solicitado por la actividad.
        tvMensaje.setText("¡Hola, esta es mi primera app Android!");
    }
}
