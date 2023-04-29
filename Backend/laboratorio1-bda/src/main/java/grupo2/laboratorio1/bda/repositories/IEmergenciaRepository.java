package grupo2.laboratorio1.bda.repositories;

import java.util.List;

import grupo2.laboratorio1.bda.models.Emergencia;

public interface IEmergenciaRepository {
    public Emergencia createEmergencia(Emergencia emergencia);
    public Emergencia getEmergencia(Integer id_emergencia);
    public List<Emergencia> getAllEmergencias();
    public Emergencia updateEmergencia(Emergencia emergencia);
    public Boolean deleteEmergencia(Integer id_emergencia);
}
