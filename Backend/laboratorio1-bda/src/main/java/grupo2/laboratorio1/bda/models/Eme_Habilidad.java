package grupo2.laboratorio1.bda.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Eme_Habilidad {

    private Integer idEmeHabilidad;
    private Integer idEmergencia;
    private Integer idHabilidad;

    public Eme_Habilidad(Integer idEmergencia, Integer idHabilidad) {
        this.idEmergencia = idEmergencia;
        this.idHabilidad = idHabilidad;
    }
}
