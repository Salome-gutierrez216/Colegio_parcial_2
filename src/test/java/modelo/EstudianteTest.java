package modelo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class EstudianteTest {

    private Estudiante crear() {
        return new Estudiante("Valeria Ortiz", "Cra 3 #4-10", "3001234567", new Fecha(12, 4, 2012), "A-15", "8B");
    }

    @Test
    public void testEdadSegunElCumpleanos() {
        Estudiante e = crear();
        assertEquals(13, e.calcularEdad(new Fecha(11, 4, 2026))); // un día antes del cumpleaños
        assertEquals(14, e.calcularEdad(new Fecha(12, 4, 2026))); // el día del cumpleaños
    }

    @Test
    public void testImprimirMuestraLosDatosDelEstudiante() {
        Persona p = crear(); // se guarda como Persona pero imprime como Estudiante
        String texto = p.imprimir();

        assertTrue(texto.contains("A-15"));
        assertTrue(texto.contains("Valeria Ortiz"));
        assertTrue(texto.contains("8B"));
    }
}
