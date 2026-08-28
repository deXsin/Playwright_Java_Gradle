package org.example.tests;

import com.microsoft.playwright.Page;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.nio.file.Paths;

public class ScreenshotOnFailureExtension implements TestWatcher {

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        Object testInstance = context.getRequiredTestInstance();
        if (testInstance instanceof BaseTest) {
            BaseTest baseTest = (BaseTest) testInstance;
            if (baseTest.getPage() != null && !baseTest.getPage().isClosed()) {
                String testName = context.getDisplayName().replaceAll("[^a-zA-Z0-9_-]", "_");
                baseTest.getPage().screenshot(new Page.ScreenshotOptions()
                        .setPath(Paths.get("build/screenshots/" + testName + ".png")));
                System.out.println("Screenshot guardado en build/screenshots/" + testName + ".png");
            }
        }
    }
}
