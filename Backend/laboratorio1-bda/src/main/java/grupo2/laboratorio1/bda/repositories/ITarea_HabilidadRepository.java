package grupo2.laboratorio1.bda.repositories;
import java.util.List;

import grupo2.laboratorio1.bda.models.Tarea_Habilidad;

public interface ITarea_HabilidadRepository {
    
    void createTarea_Habilidad(Tarea_Habilidad tarea_habilidad);
    List<Tarea_Habilidad> getAllTarea_Habilidad();
    Tarea_Habilidad getTarea_Habilidad(Integer idTareaHabilidad);

}

