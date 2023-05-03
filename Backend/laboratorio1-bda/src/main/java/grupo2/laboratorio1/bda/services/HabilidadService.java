package grupo2.laboratorio1.bda.services;

import grupo2.laboratorio1.bda.models.Habilidad;
import grupo2.laboratorio1.bda.repositories.IEmeHabilidadRepository;
import grupo2.laboratorio1.bda.repositories.IHabilidadRepository;
import grupo2.laboratorio1.bda.repositories.ITareaHabilidadRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabilidadService {
    @Autowired
    IHabilidadRepository habilidadRepository;
    @Autowired
    IEmeHabilidadRepository emeHabilidadRepository;
    @Autowired
    ITareaHabilidadRepository tareaHabilidadRepository;

    public void createHabilidad(@NonNull Integer idHabilidad,
                              String descripcion){
        Habilidad habilidad = new Habilidad(idHabilidad, descripcion);
        habilidadRepository.createHabilidad(habilidad);
    }

    public Habilidad getHabilidad(@NonNull Integer idHabilidad){
        //if(!existsHabilidad(idHabilidad)){
          //  throw new IllegalArgumentException("No existe la habilidad");
        //}
        return habilidadRepository.getHabilidad(idHabilidad);
    }

    public List<Habilidad> getAllHabilidades(){
        return habilidadRepository.getAllHabilidades();
    }

    public void updateHabilidad(Integer idHabilidad, Habilidad habilidad){
        habilidad.setIdHabilidad(idHabilidad);
        //vaildateHabilidad(habilidad);

        habilidadRepository.updateHabilidad(habilidad);
    }

    public void deleteHabilidad(Integer idHabilidad){
        if(emeHabilidadRepository.getEmeHabilidadByIdHabilidad(idHabilidad).size() > 0){
            throw new IllegalArgumentException("No se puede eliminar la habilidad porque tiene emergencias asociadas");
        }
        if(tareaHabilidadRepository.getTareaHabilidadByIdHabilidad(idHabilidad).size() > 0){
            throw new IllegalArgumentException("No se puede eliminar la habilidad porque tiene tareas asociadas");
        }
        habilidadRepository.deleteHabilidad(idHabilidad);
    }
}
