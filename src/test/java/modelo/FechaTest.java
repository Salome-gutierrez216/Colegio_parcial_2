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
}
