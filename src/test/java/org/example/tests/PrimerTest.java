package org.example.tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrimerTest extends BaseTest {

    @Test
    public void testPaginaLogin() {
        page.navigate("https://the-internet.herokuapp.com/login");
        assertEquals("The Internet", page.title());
    }
}
