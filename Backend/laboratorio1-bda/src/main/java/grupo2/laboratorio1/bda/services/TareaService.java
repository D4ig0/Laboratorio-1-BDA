package grupo2.laboratorio1.bda.services;

import grupo2.laboratorio1.bda.models.Tarea;
import grupo2.laboratorio1.bda.repositories.TareaRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class TareaService {
    @Autowired
    TareaRepository tareaRepository;

    public void createTarea(@NonNull Integer idEmergencia, String nombre, String descripcion, Integer cantVolRequeridos,
                            Integer cantVolInscritos, Date fechaInicio, Date fechaFin , String estadoActual)
    {Tarea tarea = new Tarea( null,idEmergencia, nombre, descripcion,cantVolRequeridos, cantVolInscritos, fechaInicio, fechaFin, estadoActual);
        validarTarea(tarea);
        tareaRepository.createTarea(tarea);}

    private void validarTarea(Tarea tarea){
        
    }

    public Tarea getTarea(@NonNull Integer idTarea){
        Tarea tarea = new Tarea();
        tarea.setIdTarea(idTarea);
        validarTarea(tarea);
        return tareaRepository.getTarea(idTarea);
    }

    public List<Tarea> getAllTareas(){
        return tareaRepository.getAllTareas();
    }

    public void updateTarea(Integer idTarea, Tarea tarea){
        tarea.setIdTarea(idTarea);
        validarTarea(tarea);
        tareaRepository.updateTarea(tarea);
    }


   


   }

   
