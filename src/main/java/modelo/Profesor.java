package modelo;

public class Profesor extends Persona {
    private String cedula;
    private String area;
    private double salarioHora;
    private int horasDictadas;

    public Profesor(String nombre, String direccion, String telefono, Fecha fechaNacimiento,
                    String cedula, String area, double salarioHora, int horasDictadas) {
        super(nombre, direccion, telefono, fechaNacimiento);
        this.cedula = cedula;
        this.area = area;
        this.salarioHora = salarioHora;
        this.horasDictadas = horasDictadas;
    }

    // Salario 1: con las horas que dictó en el mes
    public double calcularSalario() {
        return calcularSalario(horasDictadas);
    }

    // Salario 2 (SOBRECARGA): con una cantidad de horas dada.
    // Se suma un 30% por el tiempo de preparación de clases
    public double calcularSalario(int horas) {
        double basico = salarioHora * horas;
        return basico * 1.30;
    }

    // Las prestaciones sociales son el 19% del salario
    public double calcularPrestaciones() {
        return calcularSalario() * 0.19;
    }

    @Override
    public String imprimir() {
        return "Cédula: " + cedula + " | " + nombre
             + " | Área: " + area
             + " | Horas: " + horasDictadas
             + " | Salario: $" + Math.round(calcularSalario())
             + " | Prestaciones: $" + Math.round(calcularPrestaciones());
    }

    public String getCedula()     { return cedula; }
    public String getArea()       { return area; }
    public double getSalarioHora() { return salarioHora; }
    public int getHorasDictadas() { return horasDictadas; }
}
