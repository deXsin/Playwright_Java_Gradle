package org.example.tests;

import org.junit.jupiter.api.Test;
import org.example.pages.DashboardPage;
import org.example.pages.LoginPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoginTest extends BaseTest {

    @Test
    public void test_loginExitoso() {
        LoginPage loginPage = new LoginPage(page);
        loginPage.navigate();
        loginPage.login("tomsmith", "SuperSecretPassword!");

        DashboardPage dashboardPage = new DashboardPage(page);
        assertThat(dashboardPage.getFlashMessageLocator()).containsText("You logged into a secure area!");
    }

    @Test
    public void test_loginFallido() {
        LoginPage loginPage = new LoginPage(page);
        loginPage.navigate();
        loginPage.login("usuarioInvalido", "PasswordInvalida");

        assertThat(loginPage.getFlashMessageLocator()).containsText("Your username is invalid!");
    }
}
