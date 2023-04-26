package grupo2.laboratorio1.bda.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tarea_Habilidad {

    private Integer idTareaHabilidad;
    private Integer idEmergencia;
    private Integer idHabilidad;
    private Integer idTarea;
}