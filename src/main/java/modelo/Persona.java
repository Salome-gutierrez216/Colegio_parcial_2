package modelo;

public abstract class Persona {
    protected String nombre;
    protected String direccion;
    protected String telefono;
    protected Fecha fechaNacimiento;

    public Persona(String nombre, String direccion, String telefono, Fecha fechaNacimiento) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
    }

    // Edad 1: los años cumplidos hasta hoy
    public int calcularEdad() {
        return calcularEdad(Fecha.hoy());
    }

    // Edad 2 (SOBRECARGA): los años cumplidos hasta una fecha dada
    public int calcularEdad(Fecha referencia) {
        int edad = referencia.getAnio() - fechaNacimiento.getAnio();
        // Si todavía no ha llegado el cumpleaños ese año, se resta uno
        if (referencia.getMes() < fechaNacimiento.getMes()
                || (referencia.getMes() == fechaNacimiento.getMes() && referencia.getDia() < fechaNacimiento.getDia())) {
            edad--;
        }
        return Math.max(edad, 0);
    }

    // Cada subclase muestra sus datos a su manera (POLIMORFISMO)
    public abstract String imprimir();

    public String getNombre()          { return nombre; }
    public String getDireccion()       { return direccion; }
    public String getTelefono()        { return telefono; }
    public Fecha getFechaNacimiento()  { return fechaNacimiento; }
}
