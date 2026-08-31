package org.example.tests;

import org.junit.jupiter.api.Test;
import org.example.pages.DashboardPage;
import org.example.pages.LoginPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

// Pruebas automatizadas del flujo de inicio de sesión
public class LoginTest extends BaseTest {

    // Prueba de inicio de sesión con credenciales correctas
    @Test
    public void test_loginExitoso() {
        LoginPage loginPage = new LoginPage(page);
        loginPage.navigate();
        loginPage.login("tomsmith", "SuperSecretPassword!");

        DashboardPage dashboardPage = new DashboardPage(page);
        assertThat(dashboardPage.getFlashMessageLocator()).containsText("You logged into a secure area!");
    }

    // Prueba de validación de error con credenciales incorrectas
    @Test
    public void test_loginFallido() {
        LoginPage loginPage = new LoginPage(page);
        loginPage.navigate();
        loginPage.login("usuarioInvalido", "PasswordInvalida");

        assertThat(loginPage.getFlashMessageLocator()).containsText("Your username is invalid!");
    }
}
