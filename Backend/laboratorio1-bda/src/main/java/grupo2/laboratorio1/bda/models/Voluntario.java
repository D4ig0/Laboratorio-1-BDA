package grupo2.laboratorio1.bda.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




@Data
@AllArgsConstructor
@NoArgsConstructor
public class Voluntario {
    private Integer idVoluntario;
    private String nombre;
    private String correo;
    @JsonProperty(value = "password", access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private Double longitud;
    private Double latitud;
}
