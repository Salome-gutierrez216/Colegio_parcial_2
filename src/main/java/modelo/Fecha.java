package modelo;

public class Fecha {
    private int dia;
    private int mes;
    private int anio;

    public Fecha(int dia, int mes, int anio) {
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
    }

    public int getDia()  { return dia; }
    public int getMes()  { return mes; }
    public int getAnio() { return anio; }

    // Revisa que la fecha exista en el calendario (por ejemplo, no hay 31 de abril)
    public boolean esValida() {
        if (anio < 1900 || mes < 1 || mes > 12 || dia < 1) {
            return false;
        }
        return dia <= diasDelMes();
    }

    private int diasDelMes() {
        switch (mes) {
            case 2:
                return esBisiesto() ? 29 : 28;
            case 4: case 6: case 9: case 11:
                return 30;
            default:
                return 31;
        }
    }

    private boolean esBisiesto() {
        return (anio % 4 == 0 && anio % 100 != 0) || anio % 400 == 0;
    }

    // true si esta fecha va después de la otra
    public boolean esPosteriorA(Fecha otra) {
        if (anio != otra.anio) {
            return anio > otra.anio;
        }
        if (mes != otra.mes) {
            return mes > otra.mes;
        }
        return dia > otra.dia;
    }

    // Fecha de hoy según el reloj del computador
    public static Fecha hoy() {
        java.time.LocalDate h = java.time.LocalDate.now();
        return new Fecha(h.getDayOfMonth(), h.getMonthValue(), h.getYear());
    }

    @Override
    public String toString() {
        return dia + "/" + mes + "/" + anio;
    }
}
