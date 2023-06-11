package grupo2.laboratorio1.bda.services;

import grupo2.laboratorio1.bda.models.TareaHabilidad;
import grupo2.laboratorio1.bda.repositories.ITareaRepository;
import grupo2.laboratorio1.bda.repositories.IHabilidadRepository;
import grupo2.laboratorio1.bda.repositories.ITareaHabilidadRepository;
import grupo2.laboratorio1.bda.repositories.IEmergenciaRepository;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TareaHabilidadService {
    @Autowired
    ITareaHabilidadRepository tareaHabilidadRepository;
    @Autowired
    ITareaRepository tareaRepository;
    @Autowired
    IHabilidadRepository habilidadRepository;
    @Autowired
    IEmergenciaRepository emergenciaRepository;

    public void createTareaHabilidad(Integer idHabilidad, Integer idTarea){
        if(habilidadRepository.getHabilidad(idHabilidad) == null) {
            throw new RuntimeException("No se ha encontrado la habilidad");
        }
        if(tareaRepository.getTarea(idTarea) == null){
            throw new RuntimeException("No se ha encontrado la tarea");
        }

        TareaHabilidad tarea_habilidad = new TareaHabilidad(null, idHabilidad, idTarea);
        tareaHabilidadRepository.createTareaHabilidad(tarea_habilidad);
    }


    public TareaHabilidad getTareaHabilidad(Integer idTareaHabilidad){
        try{
            return tareaHabilidadRepository.getTareaHabilidad(idTareaHabilidad);
        } catch (Exception e){
            throw new RuntimeException("No se ha encontrado en la base de datos  tarea_habilidad");
        }
    }

    public List<TareaHabilidad> getAllTareaHabilidad(){
        return tareaHabilidadRepository.getAllTareaHabilidad();
    }


    public TareaHabilidad updateTareaHabilidad(Integer idTareaHabilidad, TareaHabilidad tareaHabilidad){
        if(getTareaHabilidad(idTareaHabilidad) == null){
            throw new IllegalArgumentException("La tarea habilidad no existe");
        }

        return tareaHabilidadRepository.updateTareaHabilidad(idTareaHabilidad, tareaHabilidad);
    }

    public void deleteTareaHabilidad(Integer idTareaHabilidad){
        if(getTareaHabilidad(idTareaHabilidad) == null){
            throw new IllegalArgumentException("La tarea habilidad no existe");
        }

        tareaHabilidadRepository.deleteTareaHabilidad(idTareaHabilidad);
    }


}
