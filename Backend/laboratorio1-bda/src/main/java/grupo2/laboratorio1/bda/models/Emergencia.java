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
public class Emergencia {
    private Integer idEmergencia;
    private String nombre;
    private String descripcion;
    private Date fecha_inicio;
    private Date fecha_termino;
    private Boolean activo;
    private Integer idInstitucion;
    private String nombreInstitucion;
    private Integer tareasActivas;

    private Double longitud;
    private Double latitud;
}
