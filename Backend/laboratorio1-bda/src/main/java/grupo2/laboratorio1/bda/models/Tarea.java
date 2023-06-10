

package grupo2.laboratorio1.bda.models;

import java.sql.Date;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Tarea {

    private Integer idTarea;
    private Integer idEmergencia;
    private String nombre;
    private String descripcion;
    private Integer cantVolRequeridos;
    private Integer cantVolInscritos;
    private Date fechaInicio;
    private Date fechaFin;
    private String estadoActual;

    private Double latitud;
    private Double longitud;
}
