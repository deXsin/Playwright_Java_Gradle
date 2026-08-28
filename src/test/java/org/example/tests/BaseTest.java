package org.example.tests;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.File;
import java.nio.file.Paths;

@ExtendWith(ScreenshotOnFailureExtension.class)
public abstract class BaseTest {

    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;

    @BeforeEach
    public void setUp() {
        playwright = Playwright.create();
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(headless)
        );
        context = browser.newContext();

        // Configurar Tracing (Etapa 4)
        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));

        page = context.newPage();
    }

    public Page getPage() {
        return page;
    }

    @AfterEach
    public void tearDown(TestInfo testInfo) {
        if (context != null) {
            String testName = testInfo.getTestMethod()
                    .map(java.lang.reflect.Method::getName)
                    .orElse(testInfo.getDisplayName())
                    .replaceAll("[^a-zA-Z0-9_-]", "_");
            
            new File("build/traces").mkdirs();
            context.tracing().stop(new Tracing.StopOptions()
                    .setPath(Paths.get("build/traces/" + testName + ".zip")));
            context.close();
        }
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }
}
