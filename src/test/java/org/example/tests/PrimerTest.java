package org.example.tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

// Test básico de verificación de título de página
public class PrimerTest extends BaseTest {

    // Comprueba que la página cargue con el título esperado
    @Test
    public void testPaginaLogin() {
        page.navigate("https://the-internet.herokuapp.com/login");
        assertEquals("The Internet", page.title());
    }
}
