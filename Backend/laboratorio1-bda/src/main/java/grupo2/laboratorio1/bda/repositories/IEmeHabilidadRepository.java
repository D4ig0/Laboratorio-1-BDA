package grupo2.laboratorio1.bda.repositories;

import java.util.List;

import grupo2.laboratorio1.bda.models.EmeHabilidad;

public interface IEmeHabilidadRepository {
    void createEmeHabilidad(EmeHabilidad emeHabilidad);
    EmeHabilidad getEmeHabilidad(Integer idEmeHabilidad);
    List<EmeHabilidad> getAllEmeHabilidad();
    EmeHabilidad updateEmeHabilidad(Integer idEmeHabilidad, EmeHabilidad emeHabilidad);
    void deleteEmeHabilidad(Integer idEmeHabilidad);
    List<EmeHabilidad> getEmeHabilidadByIdHabilidad(Integer idHabilidad);
    List<EmeHabilidad> getEmeHabilidadByIdEmergencia(Integer idEmergencia);
}
