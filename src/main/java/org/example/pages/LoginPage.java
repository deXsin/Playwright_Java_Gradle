package org.example.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class LoginPage {
    private final Page page;

    private final Locator usernameInput;
    private final Locator passwordInput;
    private final Locator submitButton;
    private final Locator flashMessage;

    public LoginPage(Page page) {
        this.page = page;
        this.usernameInput = page.locator("#username");
        this.passwordInput = page.locator("#password");
        this.submitButton = page.locator("button[type='submit']");
        this.flashMessage = page.locator("#flash");
    }

    public void navigate() {
        page.navigate("https://the-internet.herokuapp.com/login");
    }

    public void enterUsername(String username) {
        usernameInput.fill(username);
    }

    public void enterPassword(String password) {
        passwordInput.fill(password);
    }

    public void clickLogin() {
        submitButton.click();
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    public Locator getFlashMessageLocator() {
        return flashMessage;
    }

    public String getFlashMessage() {
        return flashMessage.textContent().trim();
    }
}
