package controlador;

import java.util.ArrayList;
import modelo.Estudiante;
import modelo.Fecha;
import modelo.Profesor;

public class ControladorRegistro {
    private ArrayList<Estudiante> estudiantes = new ArrayList<>();
    private ArrayList<Profesor> profesores = new ArrayList<>();
    private Fecha hoy;
    private String mensaje = "";

    // Constructor 1: usa la fecha real del computador
    public ControladorRegistro() {
        this(Fecha.hoy());
    }

    // Constructor 2 (SOBRECARGA): permite fijar la fecha de hoy (sirve para las pruebas)
    public ControladorRegistro(Fecha hoy) {
        this.hoy = hoy;
    }

    // Registra un estudiante. Devuelve false si algún dato está mal (ver getMensaje)
    public boolean registrarEstudiante(String nombre, String direccion, String telefono,
                                       Fecha nacimiento, String codigo, String grado) {
        if (hayVacios(nombre, direccion, telefono, codigo, grado) || !fechaCorrecta(nacimiento)) {
            return false;
        }
        if (buscarEstudiante(codigo.trim()) != null) {
            mensaje = "Ya hay un estudiante con el código " + codigo.trim();
            return false;
        }
        estudiantes.add(new Estudiante(nombre.trim(), direccion.trim(), telefono.trim(),
                nacimiento, codigo.trim(), grado.trim()));
        mensaje = "Estudiante registrado";
        return true;
    }

    // Registra un profesor. Devuelve false si algún dato está mal (ver getMensaje)
    public boolean registrarProfesor(String nombre, String direccion, String telefono, Fecha nacimiento,
                                     String cedula, String area, double salarioHora, int horas) {
        if (hayVacios(nombre, direccion, telefono, cedula, area) || !fechaCorrecta(nacimiento)) {
            return false;
        }
        if (salarioHora <= 0 || horas <= 0) {
            mensaje = "El salario por hora y las horas deben ser mayores que cero";
            return false;
        }
        Profesor p = new Profesor(nombre.trim(), direccion.trim(), telefono.trim(), nacimiento,
                cedula.trim(), area.trim(), salarioHora, horas);
        if (p.calcularEdad(hoy) < 18) {
            mensaje = "El profesor debe tener al menos 18 años";
            return false;
        }
        if (buscarProfesor(cedula.trim()) != null) {
            mensaje = "Ya hay un profesor con la cédula " + cedula.trim();
            return false;
        }
        profesores.add(p);
        mensaje = "Profesor registrado";
        return true;
    }

    // Busca un estudiante por su código. Devuelve null si no existe
    public Estudiante buscarEstudiante(String codigo) {
        for (Estudiante e : estudiantes) {
            if (e.getCodigo().equals(codigo)) {
                return e;
            }
        }
        return null;
    }

    // Busca un profesor por su cédula. Devuelve null si no existe
    public Profesor buscarProfesor(String cedula) {
        for (Profesor p : profesores) {
            if (p.getCedula().equals(cedula)) {
                return p;
            }
        }
        return null;
    }

    // Reporte 1: todos los estudiantes en el orden en que se registraron
    public String reporteEstudiantes() {
        if (estudiantes.isEmpty()) {
            return "No se ha registrado ningún estudiante.";
        }
        String texto = "LISTADO DE ESTUDIANTES (" + estudiantes.size() + ")\n\n";
        for (Estudiante e : estudiantes) {
            texto += e.imprimir() + "\n";
        }
        return texto;
    }

    // Reporte 2: profesores del que más gana al que menos, con el total de prestaciones
    public String reporteProfesores() {
        if (profesores.isEmpty()) {
            return "No se ha registrado ningún profesor.";
        }
        String texto = "PROFESORES POR SALARIO (de mayor a menor)\n\n";
        for (Profesor p : profesoresPorSalario()) {
            texto += p.imprimir() + "\n";
        }
        texto += "\nTotal prestaciones sociales: $" + Math.round(totalPrestaciones());
        return texto;
    }

    // Copia de la lista ordenada; la lista original queda igual
    public ArrayList<Profesor> profesoresPorSalario() {
        ArrayList<Profesor> copia = new ArrayList<>(profesores);
        copia.sort((a, b) -> Double.compare(b.calcularSalario(), a.calcularSalario()));
        return copia;
    }

    public double totalPrestaciones() {
        double total = 0;
        for (Profesor p : profesores) {
            total += p.calcularPrestaciones();
        }
        return total;
    }

    private boolean hayVacios(String... campos) {
        for (String campo : campos) {
            if (campo == null || campo.trim().isEmpty()) {
                mensaje = "Hay campos vacíos, todos son obligatorios";
                return true;
            }
        }
        return false;
    }

    private boolean fechaCorrecta(Fecha f) {
        if (f == null || !f.esValida()) {
            mensaje = "La fecha de nacimiento no existe";
            return false;
        }
        if (f.esPosteriorA(hoy)) {
            mensaje = "La fecha de nacimiento no puede ser futura";
            return false;
        }
        return true;
    }

    public String getMensaje() { return mensaje; }
    public ArrayList<Estudiante> getEstudiantes() { return estudiantes; }
    public ArrayList<Profesor> getProfesores()    { return profesores; }
}
