package org.example.tests;

import org.junit.jupiter.api.Test;
import org.example.pages.DashboardPage;
import org.example.pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest extends BaseTest {

    @Test
    public void test_loginExitoso() {
        LoginPage loginPage = new LoginPage(page);
        loginPage.navigate();
        loginPage.login("tomsmith", "SuperSecretPassword!");

        DashboardPage dashboardPage = new DashboardPage(page);
        assertTrue(dashboardPage.getFlashMessage().contains("You logged into a secure area!"));
        assertTrue(dashboardPage.isLogoutButtonVisible());
    }

    @Test
    public void test_loginFallido() {
        LoginPage loginPage = new LoginPage(page);
        loginPage.navigate();
        loginPage.login("usuarioInvalido", "PasswordInvalida");

        assertTrue(loginPage.getFlashMessage().contains("Your username is invalid!"));
    }
}
