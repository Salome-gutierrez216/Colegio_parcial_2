package modelo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class FechaTest {

    @Test
    public void testFechaQueNoExiste() {
        assertTrue(new Fecha(30, 4, 2010).esValida());
        assertFalse(new Fecha(31, 4, 2010).esValida()); // abril tiene 30 días
        assertFalse(new Fecha(10, 13, 2010).esValida()); // no hay mes 13
    }

    @Test
    public void testFebreroEnAnioBisiesto() {
        assertTrue(new Fecha(29, 2, 2024).esValida());  // 2024 es bisiesto
        assertFalse(new Fecha(29, 2, 2023).esValida()); // 2023 no
    }
}
