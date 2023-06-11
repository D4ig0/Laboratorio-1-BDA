package grupo2.laboratorio1.bda.repositories;

import java.util.List;

import grupo2.laboratorio1.bda.models.Emergencia;

public interface IEmergenciaRepository {
    Emergencia createEmergencia(Emergencia emergencia, double x, double y);
    Emergencia getEmergencia(Integer id_emergencia);
    List<Emergencia> getAllEmergencias();
    Emergencia updateEmergencia(Emergencia emergencia);
    Boolean deleteEmergencia(Integer id_emergencia);

    List<Emergencia> getAllEmergenciasExtraData();
}
