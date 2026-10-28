package modelo;

public class Estudiante extends Persona {
    private String codigo;
    private String grado;

    public Estudiante(String nombre, String direccion, String telefono, Fecha fechaNacimiento,
                      String codigo, String grado) {
        super(nombre, direccion, telefono, fechaNacimiento);
        this.codigo = codigo;
        this.grado = grado;
    }

    @Override
    public String imprimir() {
        return "Código: " + codigo + " | " + nombre
             + " | Grado " + grado
             + " | Nacimiento: " + fechaNacimiento
             + " | Tel: " + telefono
             + " | Dir: " + direccion;
    }

    public String getCodigo() { return codigo; }
    public String getGrado()  { return grado; }
}
