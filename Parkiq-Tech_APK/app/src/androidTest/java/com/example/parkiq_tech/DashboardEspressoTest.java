package com.example.parkiq_tech;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewAssertion;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.parkiq_tech.ui.dashboard.DashboardActivity;

import org.junit.Rule;
import org.junit.Test;

/**
 * Pruebas de interfaz (Espresso) del módulo Dashboard (mapa de espacios).
 * Se abre la pantalla directamente, enviando los mismos datos que enviaría el Login.
 */
public class DashboardEspressoTest {

    // Crea el Intent con los datos que normalmente envía LoginActivity.
    private static Intent intentDashboard() {
        Context contexto = ApplicationProvider.getApplicationContext();
        Intent intent = new Intent(contexto, DashboardActivity.class);
        intent.putExtra("rol", "ADMIN");
        intent.putExtra("nombre", "Administrador");
        return intent;
    }

    @Rule
    public ActivityScenarioRule<DashboardActivity> reglaActividad =
            new ActivityScenarioRule<>(intentDashboard());

    // Verificación personalizada: el RecyclerView debe tener al menos un elemento.
    private static ViewAssertion tieneElementos() {
        return new ViewAssertion() {
            @Override
            public void check(View vista, NoMatchingViewException noViewFoundException) {
                if (noViewFoundException != null) {
                    throw noViewFoundException;
                }
                RecyclerView lista = (RecyclerView) vista;
                assertNotNull("El RecyclerView no tiene Adapter", lista.getAdapter());
                assertTrue("El mapa no muestra ningún espacio",
                        lista.getAdapter().getItemCount() > 0);
            }
        };
    }

    // PRUEBA 5: el Dashboard muestra el título y el saludo con nombre y rol
    @Test
    public void dashboard_muestraTituloYSaludo() {
        onView(withId(R.id.tvBienvenida))
                .check(matches(isDisplayed()))
                .check(matches(withText("Hola, Administrador (ADMIN)")));
        onView(withText("Mapa de espacios")).check(matches(isDisplayed()));
    }

    // PRUEBA 6: el botón de reportes está visible
    @Test
    public void dashboard_muestraBotonVerReportes() {
        onView(withId(R.id.btnVerReportes))
                .check(matches(isDisplayed()))
                .check(matches(withText("Ver reportes")));
    }

    // PRUEBA 7: el mapa de espacios está cargado con datos
    @Test
    public void dashboard_cargaElMapaDeEspacios() {
        onView(withId(R.id.rvEspacios))
                .check(matches(isDisplayed()))
                .check(tieneElementos());
    }
}
