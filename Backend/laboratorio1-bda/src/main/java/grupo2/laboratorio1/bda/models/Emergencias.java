package grupo2.laboratorio1.bda.models;

import java.io.Serial;
import java.sql.Date;

public class Emergencias {
    private Serial id_emergencia;
    private String nombre;
    private String descripcion;
    private Date fecha;
    private String estado;

    private Serial id_institucion;

    public Serial getId_emergencia() {
        return id_emergencia;
    }
    public void setId_emergencia(Serial id_emergencia) {
        this.id_emergencia = id_emergencia;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public Serial getId_institucion() {
        return id_institucion;
    }
    public void setId_institucion(Serial id_institucion) {
        this.id_institucion = id_institucion;
    }   
}
