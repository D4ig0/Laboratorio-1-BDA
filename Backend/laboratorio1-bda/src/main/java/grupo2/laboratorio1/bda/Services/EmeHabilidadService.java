package grupo2.laboratorio1.bda.services;

import grupo2.laboratorio1.bda.models.EmeHabilidad;
import grupo2.laboratorio1.bda.repositories.IEmeHabilidadRepository;
import grupo2.laboratorio1.bda.repositories.IEmergenciaRepository;
import grupo2.laboratorio1.bda.repositories.IHabilidadRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmeHabilidadService {
    @Autowired
    IEmeHabilidadRepository emeHabilidadRepository;
    @Autowired
    IEmergenciaRepository emergenciaRepository;
    @Autowired
    IHabilidadRepository habilidadRepository;

    public void createEmeHabilidad(Integer idEmergencia, Integer idHabilidad){
        try{
            emergenciaRepository.getEmergencia(idEmergencia);
        } catch ( Exception e){
            throw new RuntimeException("No se ha encontrado en la base de datos la Emergencia");
        }
        try {
            habilidadRepository.getHabilidad(idHabilidad);
        } catch (Exception e ){
            throw new RuntimeException("No se ha encontrado en la base de datos la Habilidad");
        }
        EmeHabilidad emeHabilidad = new EmeHabilidad(idEmergencia, idHabilidad);
        emeHabilidadRepository.createEmeHabilidad(emeHabilidad);
    }

    public EmeHabilidad getEmeHabilidad(Integer idEmeHabilidad){
        try{
           return  emeHabilidadRepository.getEmeHabilidad(idEmeHabilidad);
        } catch (Exception e){
            throw new RuntimeException("No se ha encontrado en la base de datos la EmeHabilidad");
        }
    }

    public List<EmeHabilidad> getAllEmeHabilidad(){
        return emeHabilidadRepository.getAllEmeHabilidad();
    }

    public EmeHabilidad updateEmeHabilidad(Integer idEmeHabilidad, EmeHabilidad emeHabilidad){
        try{
            getEmeHabilidad(idEmeHabilidad);
        } catch (Exception e){
            throw new RuntimeException("No se ha encontrado en la base de datos la EmeHabilidad");
        }
        return emeHabilidadRepository.updateEmeHabilidad(idEmeHabilidad, emeHabilidad);
    }

    public void deleteEmeHabilidad(Integer idEmeHabilidad){
        try{
            getEmeHabilidad(idEmeHabilidad);
        } catch (Exception e){
            throw new RuntimeException("No se ha encontrado en la base de datos la EmeHabilidad");
        }
        emeHabilidadRepository.deleteEmeHabilidad(idEmeHabilidad);
    }
}
