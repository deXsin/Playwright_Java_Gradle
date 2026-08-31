package org.example.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

// Representa la página del panel/dashboard (Page Object Model)
public class DashboardPage {
    private final Page page;

    // Locators de los elementos de la página
    private final Locator header;
    private final Locator flashMessage;
    private final Locator logoutButton;

    // Constructor: inicializa la página y sus elementos
    public DashboardPage(Page page) {
        this.page = page;
        this.header = page.locator("h2");
        this.flashMessage = page.locator("#flash");
        this.logoutButton = page.locator("a.button[href='/logout']");
    }

    // Retorna el locator del botón de logout
    public Locator getLogoutButtonLocator() {
        return logoutButton;
    }

    // Retorna el locator del mensaje flash (alerta)
    public Locator getFlashMessageLocator() {
        return flashMessage;
    }

    // Obtiene el texto del título principal
    public String getHeaderText() {
        return header.textContent().trim();
    }

    // Obtiene el texto del mensaje flash
    public String getFlashMessage() {
        return flashMessage.textContent().trim();
    }

    // Verifica si el botón de logout es visible
    public boolean isLogoutButtonVisible() {
        return logoutButton.isVisible();
    }

    // Hace clic en el botón de logout
    public void clickLogout() {
        logoutButton.click();
    }
}
