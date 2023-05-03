package grupo2.laboratorio1.bda.services;

import grupo2.laboratorio1.bda.models.VolEmergencia;
import grupo2.laboratorio1.bda.repositories.IVolEmergenciaRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VolEmergenciaService {
    @Autowired
    private IVolEmergenciaRepository volEmergenciaRepository;

    public void createVolEmergencia(Integer idVoluntario, Integer idEmergencia){
        try {
            VolEmergencia volEmergencia = new VolEmergencia();
            volEmergencia.setIdVoluntario(idVoluntario);
            volEmergencia.setIdEmergencia(idEmergencia);
            volEmergenciaRepository.createVolEmergencia(volEmergencia);
        }
        catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
        catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public VolEmergencia getVolEmergencia(Integer idVolEmergencia){
        try{
            return volEmergenciaRepository.getVolEmergencia(idVolEmergencia);
        }
        catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
        catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<VolEmergencia> getAllVolEmergencia(){
        try{
            return volEmergenciaRepository.getAllVolEmergencia();
        }
        catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public VolEmergencia updateVolEmergencia(Integer idVolEmergencia, VolEmergencia volEmergencia){
        try{
            getVolEmergencia(idVolEmergencia);
        } catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
        return volEmergenciaRepository.updateVolEmergencia(idVolEmergencia, volEmergencia);

    }

    public void deleteVolEmergencia(Integer idVolEmergencia){
        try{
            getVolEmergencia(idVolEmergencia);
        } catch (Exception e){
            throw new RuntimeException("no se ha encontrado el id de la tabla VolEmergencia");
        }
        volEmergenciaRepository.deleteVolEmergencia(idVolEmergencia);
    }
}