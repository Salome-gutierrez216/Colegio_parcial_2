package modelo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProfesorTest {

    private Profesor profe;

    @BeforeEach
    public void crearProfesor() {
        // 15.000 por hora y 120 horas dictadas
        profe = new Profesor("Jorge Mesa", "Calle 9", "3105556677", new Fecha(3, 7, 1979),
                "4455", "Química", 15000, 120);
    }

    @Test
    public void testSalarioConPreparacion() {
        // 15.000 x 120 = 1.800.000 y con el 30% queda en 2.340.000
        assertEquals(2340000, profe.calcularSalario(), 0.01);
    }

    @Test
    public void testSalarioConOtrasHoras() {
        // Sobrecarga: 15.000 x 60 = 900.000 y con el 30% queda en 1.170.000
        assertEquals(1170000, profe.calcularSalario(60), 0.01);
    }
}
