package grupo2.laboratorio1.bda.repositories;

import java.util.List;

import grupo2.laboratorio1.bda.models.Vol_Emergencia;

public interface IVol_EmergenciaRepository {

    void createVol_Emergencia(Vol_Emergencia vol_Emergencia);
    Vol_Emergencia getVol_Emergencia(Integer idVolEmergencia);
    List<Vol_Emergencia> getAllVol_Emergencia();
    Vol_Emergencia updateVol_Emergencia(Integer idVolEmergencia,Vol_Emergencia vol_Emergencia);
    void deleteVol_Emergencia(Integer idVolEmergencia);
}
