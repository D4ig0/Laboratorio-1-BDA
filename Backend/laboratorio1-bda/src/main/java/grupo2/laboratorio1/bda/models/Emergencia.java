package grupo2.laboratorio1.bda.models;

import java.io.Serial;
import java.sql.Date;

import lombok.Data;

@Data
public class Emergencia {
    private Serial id_emergencia;
    private String nombre;
    private String descripcion;
    private Date fecha_inicio;
    private Date fecha_fin;
    private Boolean activo;
    private Serial id_institucion;
}
