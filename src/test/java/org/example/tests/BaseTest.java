package org.example.tests;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.File;
import java.nio.file.Paths;

// Clase base para tests: gestiona ciclo de vida de Playwright y captura de fallos
@ExtendWith(ScreenshotOnFailureExtension.class)
public abstract class BaseTest {

    // Instancias principales de Playwright
    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;

    // Se ejecuta antes de cada test: inicia navegador, contexto, tracing y página
    @BeforeEach
    public void setUp() {
        playwright = Playwright.create();
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(headless)
        );
        context = browser.newContext();

        // Inicia la grabación del trace (capturas, snapshots y código fuente)
        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));

        page = context.newPage();
    }

    // Permite acceder a la página actual (usado por extensiones)
    public Page getPage() {
        return page;
    }

    // Se ejecuta después de cada test: guarda el trace y cierra recursos
    @AfterEach
    public void tearDown(TestInfo testInfo) {
        if (context != null) {
            String testName = testInfo.getTestMethod()
                    .map(java.lang.reflect.Method::getName)
                    .orElse(testInfo.getDisplayName())
                    .replaceAll("[^a-zA-Z0-9_-]", "_");
            
            // Guarda el archivo ZIP del trace para depuración
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
