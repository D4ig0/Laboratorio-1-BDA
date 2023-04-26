

package grupo2.laboratorio1.bda.models;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tarea {

    private Integer idTarea;
    private Integer idEmergencia; //FK

    private String nombre;
    private String descripcion;
    private Integer cantVolRequeridos;
    private Integer cantVolInscritos;
    private Date fechaInicio;
    private Date fechaFin;

    private String estadoActual;

}
