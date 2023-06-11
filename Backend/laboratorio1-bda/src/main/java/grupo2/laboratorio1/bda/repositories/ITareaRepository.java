package grupo2.laboratorio1.bda.repositories;
import java.util.List;

import grupo2.laboratorio1.bda.models.Tarea;

public interface ITareaRepository {
    void createTarea(Tarea tarea);
     Tarea getTarea(Integer idTarea);
     List<Tarea> getAllTareas();    
     void updateTarea(Tarea tarea);
     boolean existsTarea(Integer idTarea);
     void deleteTarea(Integer idTarea) ;
     Integer getTotalTareasByEmergencia(Integer idEmergencia);
     List<Tarea> getTareasEnRegion(Integer idRegion);
}
