package grupo2.laboratorio1.bda.models;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Emergencia {
    private Integer idEmergencia;
    private String nombre;
    private String descripcion;
    private Date fecha_inicio;
    private Date fecha_termino;
    private Boolean activo;
    private Integer idInstitucion;
}
