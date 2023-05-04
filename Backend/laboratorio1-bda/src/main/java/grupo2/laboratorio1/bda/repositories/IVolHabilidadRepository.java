package grupo2.laboratorio1.bda.repositories;

import java.util.Collection;
import java.util.List;

import grupo2.laboratorio1.bda.models.VolHabilidad;

public interface IVolHabilidadRepository {

    void createVolHabilidad(VolHabilidad volHabilidad);
    VolHabilidad getVolHabilidad(Integer idVolHabilidad);
    List<VolHabilidad> getAllVolHabilidad();
    VolHabilidad updateVolHabilidad(Integer idVolHabilidad, VolHabilidad volHabilidad);
    void deleteVolHabilidad(Integer idVolHabilidad);
    List<VolHabilidad> getVolHabilidadByHabilidad(Integer idEmergencia);
    List<VolHabilidad> getVolHabilidadByVoluntario(Integer idVoluntario);
}
