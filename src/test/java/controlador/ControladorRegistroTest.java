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

    @Test
    public void testNoRepiteCodigoDeEstudiante() {
        control.registrarEstudiante("Valeria", "Cra 3", "300", new Fecha(12, 4, 2012), "A-15", "8B");
        boolean ok = control.registrarEstudiante("Mateo", "Cra 7", "301", new Fecha(2, 2, 2013), "A-15", "7A");

        assertFalse(ok);
        assertEquals(1, control.getEstudiantes().size());
        assertTrue(control.getMensaje().contains("A-15"));
    }

    @Test
    public void testCamposVacios() {
        boolean ok = control.registrarEstudiante("Valeria", "   ", "300", new Fecha(12, 4, 2012), "A-15", "8B");

        assertFalse(ok);
        assertTrue(control.getEstudiantes().isEmpty());
        assertTrue(control.getMensaje().contains("vacíos"));
    }

    @Test
    public void testFechaDeNacimientoFutura() {
        // Hoy es 28/10/2026, el 29/10/2026 todavía no ha llegado
        boolean ok = control.registrarEstudiante("Valeria", "Cra 3", "300", new Fecha(29, 10, 2026), "A-15", "8B");

        assertFalse(ok);
        assertTrue(control.getMensaje().contains("futura"));
    }

    @Test
    public void testProfesorDebeSerMayorDeEdad() {
        // Nacido el 29/10/2008: le falta un día para cumplir 18
        boolean ok = control.registrarProfesor("Tomás Rey", "Calle 2", "310", new Fecha(29, 10, 2008),
                "7788", "Música", 15000, 40);

        assertFalse(ok);
        assertTrue(control.getProfesores().isEmpty());
    }

    @Test
    public void testHorasEnCero() {
        boolean ok = control.registrarProfesor("Tomás Rey", "Calle 2", "310", new Fecha(5, 5, 1990),
                "7788", "Música", 15000, 0);

        assertFalse(ok);
        assertTrue(control.getMensaje().contains("mayores que cero"));
    }
}
