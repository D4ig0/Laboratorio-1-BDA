package grupo2.laboratorio1.bda.services;

import grupo2.laboratorio1.bda.models.Estado_Tarea;
import grupo2.laboratorio1.bda.repositories.Estado_TareaRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Estado_TareaService {
    @Autowired
    Estado_TareaRepository estado_tareaRepository;

    public void createEstado_Tarea(@NonNull Integer idEstado_Tarea,
                                String descripcion){
        Estado_Tarea estado_tarea = new Estado_Tarea(idEstado_Tarea, descripcion);
        estado_tareaRepository.createEstado_Tarea(estado_tarea);
    }

    public Estado_Tarea getEstado_Tarea(@NonNull Integer idEstado_Tarea){
        //if(!existsEstado_Tarea(idEstado_Tarea)){
        //  throw new IllegalArgumentException("No existe el estado de la tarea");
        //}
        return estado_tareaRepository.getEstado_Tarea(idEstado_Tarea);
    }

    public List<Estado_Tarea> getAllEstado_Tareas(){
        return estado_tareaRepository.getAllEstado_Tareas();
    }

    public void updateEstado_Tarea(Integer idEstado_Tarea, Estado_Tarea estado_tarea){
        estado_tarea.setIdEstado_Tarea(idEstado_Tarea);
        //vaildateEstado_Tarea(estado_tarea);

        estado_tareaRepository.updateEstado_Tarea(estado_tarea);
    }

    public void deleteEstado_Tarea(Integer idEstado_Tarea){
        //if(!existsEstado_Tarea(idEstado_Tarea)){
        //    throw new IllegalArgumentException("No existe el estado de tarea");
        //}

        estado_tareaRepository.deleteEstado_Tarea(idEstado_Tarea);
    }
}
