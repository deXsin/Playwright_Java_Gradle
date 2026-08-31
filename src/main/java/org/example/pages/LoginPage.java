package org.example.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

// Representa la página de inicio de sesión (Page Object Model)
public class LoginPage {
    private final Page page;

    // Locators de los elementos del formulario
    private final Locator usernameInput;
    private final Locator passwordInput;
    private final Locator submitButton;
    private final Locator flashMessage;

    // Constructor: inicializa los locators en la página
    public LoginPage(Page page) {
        this.page = page;
        this.usernameInput = page.locator("#username");
        this.passwordInput = page.locator("#password");
        this.submitButton = page.locator("button[type='submit']");
        this.flashMessage = page.locator("#flash");
    }

    // Navega a la URL de login
    public void navigate() {
        page.navigate("https://the-internet.herokuapp.com/login");
    }

    // Escribe el nombre de usuario
    public void enterUsername(String username) {
        usernameInput.fill(username);
    }

    // Escribe la contraseña
    public void enterPassword(String password) {
        passwordInput.fill(password);
    }

    // Hace clic en el botón de submit (Login)
    public void clickLogin() {
        submitButton.click();
    }

    // Flujo completo de login: usuario, clave y clic
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    // Retorna el locator del mensaje de error o éxito
    public Locator getFlashMessageLocator() {
        return flashMessage;
    }

    // Obtiene el texto del mensaje flash
    public String getFlashMessage() {
        return flashMessage.textContent().trim();
    }
}
