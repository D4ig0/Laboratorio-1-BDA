package grupo2.laboratorio1.bda.services;

import grupo2.laboratorio1.bda.models.VolHabilidad;
import grupo2.laboratorio1.bda.repositories.IVolHabilidadRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VolHabilidadService {
    
    @Autowired
    private IVolHabilidadRepository volHabilidadRepository;


    public void createVolHabilidad(Integer idVoluntario, Integer idHabilidad){
        try {
            VolHabilidad volHabilidad = new VolHabilidad();
            volHabilidad.setIdVoluntario(idVoluntario);
            volHabilidad.setIdHabilidad(idHabilidad);
            volHabilidadRepository.createVolHabilidad(volHabilidad);
        }
        catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
        catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public VolHabilidad getVolHabilidad(Integer idVolHabilidad){
        try{
            return volHabilidadRepository.getVolHabilidad(idVolHabilidad);
        }
        catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
        catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<VolHabilidad> getAllVolHabilidad(){
        try{
            return volHabilidadRepository.getAllVolHabilidad();
        }
        catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public VolHabilidad updateVolHabilidad(Integer idVolHabilidad, VolHabilidad volHabilidad){
        try{
            getVolHabilidad(idVolHabilidad);
        } catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
        return volHabilidadRepository.updateVolHabilidad(idVolHabilidad, volHabilidad);

    }

    public void deleteVolHabilidad(Integer idVolHabilidad){
        try{
            volHabilidadRepository.getVolHabilidad(idVolHabilidad);
        } catch (Exception e){
            throw new RuntimeException("no se ha encontrado el id de la tabla volHabilidad");
        }
        volHabilidadRepository.deleteVolHabilidad(idVolHabilidad);
    }
}