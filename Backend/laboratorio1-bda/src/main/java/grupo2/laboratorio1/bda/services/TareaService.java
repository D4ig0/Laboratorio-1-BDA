package grupo2.laboratorio1.bda.services;

import grupo2.laboratorio1.bda.models.Tarea;
import grupo2.laboratorio1.bda.repositories.ITareaHabilidadRepository;
import grupo2.laboratorio1.bda.repositories.ITareaRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class TareaService {
    @Autowired
    ITareaRepository tareaRepository;
    @Autowired
    ITareaHabilidadRepository tareaHabilidadRepository;

    public void createTarea(@NonNull Integer idEmergencia, String nombre, String descripcion, Integer cantVolRequeridos, Integer cantVolInscritos, Date fechaInicio, Date fechaFin , String estadoActual){
        Tarea tarea = new Tarea(null,idEmergencia, nombre, descripcion,cantVolRequeridos, cantVolInscritos, fechaInicio, fechaFin, estadoActual, null, null);
        tareaRepository.createTarea(tarea);
    }
    public Tarea getTarea(@NonNull Integer idTarea){
        Tarea tarea = new Tarea();
        tarea.setIdTarea(idTarea);
        return tareaRepository.getTarea(idTarea);
    }
    public List<Tarea> getAllTareas(){
        return tareaRepository.getAllTareas();
    }
    public void updateTarea(Integer idTarea, Tarea tarea){
        tarea.setIdTarea(idTarea);
        tareaRepository.updateTarea(tarea);
    }
    public void deleteTarea(@NonNull Integer idTarea){
        if(tareaHabilidadRepository.getTareaHabilidadByIdTarea(idTarea).size() > 0){
            throw new IllegalArgumentException("No se puede eliminar la tarea porque tiene habilidades asociadas");
        }
        tareaRepository.deleteTarea(idTarea);
    }
    public Integer getTotalTareasByEmergencia(Integer idEmergenca){
        return tareaRepository.getTotalTareasByEmergencia(idEmergenca);
    }

    public List<Tarea> getTareasEnRegion(Integer idRegion){
        return tareaRepository.getTareasEnRegion(idRegion);
    }

   }

   
