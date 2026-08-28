package org.example.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class DashboardPage {
    private final Page page;

    private final Locator header;
    private final Locator flashMessage;
    private final Locator logoutButton;

    public DashboardPage(Page page) {
        this.page = page;
        this.header = page.locator("h2");
        this.flashMessage = page.locator("#flash");
        this.logoutButton = page.locator("a.button[href='/logout']");
    }

    public Locator getLogoutButtonLocator() {
        return logoutButton;
    }

    public Locator getFlashMessageLocator() {
        return flashMessage;
    }

    public String getHeaderText() {
        return header.textContent().trim();
    }

    public String getFlashMessage() {
        return flashMessage.textContent().trim();
    }

    public boolean isLogoutButtonVisible() {
        return logoutButton.isVisible();
    }

    public void clickLogout() {
        logoutButton.click();
    }
}
