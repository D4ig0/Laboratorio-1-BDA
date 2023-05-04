package grupo2.laboratorio1.bda.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ranking {
    private Integer idRanking;
    private Integer idVoluntario;
    private Integer idTarea;
    private Integer puntaje;
    private Boolean flgInvitado;
    private Boolean flgParticipa;
}
