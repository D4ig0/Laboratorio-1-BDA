package grupo2.laboratorio1.bda.services;

import grupo2.laboratorio1.bda.models.Vol_Emergencia;
import grupo2.laboratorio1.bda.repositories.Vol_EmergenciaRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Vol_EmergenciaService {
    @Autowired
    private Vol_EmergenciaRepository vol_emergenciaRepository;

    public void createVol_Emergencia(Integer idVoluntario, Integer idEmergencia){
        try {
            Vol_Emergencia vol_Emergencia = new Vol_Emergencia();
            vol_Emergencia.setIdVoluntario(idVoluntario);
            vol_Emergencia.setIdEmergencia(idEmergencia);
            vol_emergenciaRepository.createVol_Emergencia(vol_Emergencia);
        }
        catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
        catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public Vol_Emergencia getVol_Emergencia(Integer idVolEmergencia){
        try{
            return vol_emergenciaRepository.getVol_Emergencia(idVolEmergencia);
        }
        catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
        catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<Vol_Emergencia> getAllVol_Emergencia(){
        try{
            return vol_emergenciaRepository.getAllVol_Emergencia();
        }
        catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public Vol_Emergencia updateVol_Emergencia(Integer idVolEmergencia, Vol_Emergencia vol_Emergencia){
        try{
            getVol_Emergencia(idVolEmergencia);
        } catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
        return vol_emergenciaRepository.updateVol_Emergencia(idVolEmergencia, vol_Emergencia);

    }

    public void deleteVol_Emergencia(Integer idVolEmergencia){
        try{
            getVol_Emergencia(idVolEmergencia);
        } catch (Exception e){
            throw new RuntimeException("no se ha encontrado el id de la tabla Vol_Emergencia");
        }
        vol_emergenciaRepository.deleteVol_Emergencia(idVolEmergencia);
    }
}