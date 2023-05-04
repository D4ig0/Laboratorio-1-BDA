package grupo2.laboratorio1.bda.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstadoTarea {
    private Integer idEstadoTarea;
    private String descripcion;
}