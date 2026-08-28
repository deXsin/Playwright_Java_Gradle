package org.example.pages;

import com.microsoft.playwright.Page;

public class DashboardPage {
    private final Page page;

    private final String header = "h2";
    private final String flashMessage = "#flash";
    private final String logoutButton = "a.button[href='/logout']";

    public DashboardPage(Page page) {
        this.page = page;
    }

    public String getHeaderText() {
        return page.textContent(header).trim();
    }

    public String getFlashMessage() {
        return page.textContent(flashMessage).trim();
    }

    public boolean isLogoutButtonVisible() {
        return page.isVisible(logoutButton);
    }

    public void clickLogout() {
        page.click(logoutButton);
    }
}
