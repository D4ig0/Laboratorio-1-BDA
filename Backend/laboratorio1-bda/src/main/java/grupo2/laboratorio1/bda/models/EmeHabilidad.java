package grupo2.laboratorio1.bda.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmeHabilidad {

    private Integer idEmeHabilidad;
    private Integer idEmergencia;
    private Integer idHabilidad;

    public EmeHabilidad(Integer idEmergencia, Integer idHabilidad) {
        this.idEmergencia = idEmergencia;
        this.idHabilidad = idHabilidad;
    }
}
