package controlador;

import modelo.Fecha;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ControladorRegistroTest {

    private ControladorRegistro control;

    @BeforeEach
    public void iniciar() {
        // Se fija "hoy" en el 28/10/2026 para que las pruebas den igual cualquier día
        control = new ControladorRegistro(new Fecha(28, 10, 2026));
    }

    @Test
    public void testRegistrarEstudiante() {
        boolean ok = control.registrarEstudiante(" Valeria Ortiz ", "Cra 3", "3001234567",
                new Fecha(12, 4, 2012), "A-15", "8B");

        assertTrue(ok);
        assertEquals(1, control.getEstudiantes().size());
        assertEquals("Valeria Ortiz", control.buscarEstudiante("A-15").getNombre());
    }
}
