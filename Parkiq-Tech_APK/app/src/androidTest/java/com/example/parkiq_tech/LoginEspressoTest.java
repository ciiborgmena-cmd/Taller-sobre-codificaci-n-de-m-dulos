package com.example.parkiq_tech;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.matcher.ViewMatchers.Visibility;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.parkiq_tech.ui.login.LoginActivity;

import org.junit.Rule;
import org.junit.Test;

/**
 * Pruebas de interfaz (Espresso) del módulo Login de Parkiq Tech.
 * Simulan lo que haría un usuario real en el celular.
 */
public class LoginEspressoTest {

    // Abre LoginActivity antes de cada prueba y la cierra al terminar.
    @Rule
    public ActivityScenarioRule<LoginActivity> reglaActividad =
            new ActivityScenarioRule<>(LoginActivity.class);

    // PRUEBA 1: credenciales correctas -> entra al Dashboard
    @Test
    public void loginCorrecto_abreElDashboard() {
        onView(withId(R.id.etUsuario)).perform(typeText("admin"), closeSoftKeyboard());
        onView(withId(R.id.etContrasena)).perform(typeText("1234"), closeSoftKeyboard());
        onView(withId(R.id.btnLogin)).perform(click());

        // Si el login funcionó, LoginActivity se cerró (finish) y el campo ya no existe.
        onView(withId(R.id.etUsuario)).check(doesNotExist());
    }

    // PRUEBA 2: contraseña incorrecta -> muestra error y se queda en el Login
    @Test
    public void loginIncorrecto_muestraMensajeDeError() {
        onView(withId(R.id.etUsuario)).perform(typeText("admin"), closeSoftKeyboard());
        onView(withId(R.id.etContrasena)).perform(typeText("clave_mala"), closeSoftKeyboard());
        onView(withId(R.id.btnLogin)).perform(click());

        onView(withId(R.id.tvError))
                .check(matches(isDisplayed()))
                .check(matches(withText("Usuario o contraseña incorrectos.")));
    }

    // PRUEBA 3: campos vacíos -> muestra la validación
    @Test
    public void camposVacios_muestraValidacion() {
        onView(withId(R.id.btnLogin)).perform(click());

        onView(withId(R.id.tvError))
                .check(matches(isDisplayed()))
                .check(matches(withText("Por favor completa usuario y contraseña.")));
    }

    // PRUEBA 4 (extra): al abrir la app, el mensaje de error está oculto
    @Test
    public void alAbrir_elMensajeDeErrorEstaOculto() {
        onView(withId(R.id.tvError))
                .check(matches(withEffectiveVisibility(Visibility.GONE)));
    }
    // PRUEBA 8 (integración): login correcto -> el Dashboard carga el mapa de espacios
    @Test
    public void loginCorrecto_cargaElMapaDeEspacios() {
        onView(withId(R.id.etUsuario)).perform(typeText("admin"), closeSoftKeyboard());
        onView(withId(R.id.etContrasena)).perform(typeText("1234"), closeSoftKeyboard());
        onView(withId(R.id.btnLogin)).perform(click());

        // Ya estamos en el Dashboard: el mapa debe estar visible
        onView(withId(R.id.rvEspacios)).check(matches(isDisplayed()));
        onView(withText("Mapa de espacios")).check(matches(isDisplayed()));
    }
}