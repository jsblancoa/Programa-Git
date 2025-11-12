package modelo;

public class Usuario {
    private int id;
    private String nombre;
    private String correo;
    private String clave;

    public Usuario() {}

    public Usuario(String nombre, String correo, String clave) {
        this.nombre = nombre;
        this.correo = correo;
        this.clave = clave;
    }

    // Getters y Setters
}
