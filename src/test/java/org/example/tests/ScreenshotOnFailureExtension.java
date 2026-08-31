package org.example.tests;

import com.microsoft.playwright.Page;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.nio.file.Paths;

// Extensión de JUnit 5 que captura pantalla automáticamente si un test falla
public class ScreenshotOnFailureExtension implements TestWatcher {

    // Se invoca cuando un test termina con fallo/error
    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        Object testInstance = context.getRequiredTestInstance();
        // Verifica si el test hereda de BaseTest para obtener su instancia de Page
        if (testInstance instanceof BaseTest) {
            BaseTest baseTest = (BaseTest) testInstance;
            if (baseTest.getPage() != null && !baseTest.getPage().isClosed()) {
                // Guarda la captura PNG en build/screenshots/ con el nombre del test
                String testName = context.getDisplayName().replaceAll("[^a-zA-Z0-9_-]", "_");
                baseTest.getPage().screenshot(new Page.ScreenshotOptions()
                        .setPath(Paths.get("build/screenshots/" + testName + ".png")));
                System.out.println("Screenshot guardado en build/screenshots/" + testName + ".png");
            }
        }
    }
}
