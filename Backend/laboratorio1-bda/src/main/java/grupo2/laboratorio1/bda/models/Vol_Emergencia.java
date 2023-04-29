package grupo2.laboratorio1.bda.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vol_Emergencia {

    private Integer idVolEmergencia;
    private Integer idVoluntario;
    private Integer idEmergencia;

}
