package grupo2.laboratorio1.bda.repositories;

import java.util.List;

import grupo2.laboratorio1.bda.models.VolEmergencia;

public interface IVolEmergenciaRepository {

    void createVolEmergencia(VolEmergencia volEmergencia);
    VolEmergencia getVolEmergencia(Integer idVolEmergencia);
    List<VolEmergencia> getAllVolEmergencia();
    VolEmergencia updateVolEmergencia(Integer idVolEmergencia, VolEmergencia volEmergencia);
    void deleteVolEmergencia(Integer idVolEmergencia);
}
