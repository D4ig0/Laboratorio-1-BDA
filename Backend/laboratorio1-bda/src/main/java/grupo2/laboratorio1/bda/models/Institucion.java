package grupo2.laboratorio1.bda.models;

import java.io.Serial;

public class Institucion {
    private Serial id_institucion;
    private String nombre;
    private String direccion;

    public Serial getId_institucion() {
        return id_institucion;
    }
    public void setId_institucion(Serial id_institucion) {
        this.id_institucion = id_institucion;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
