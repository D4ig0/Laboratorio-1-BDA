package grupo2.laboratorio1.bda.repositories;
import java.util.List;

import grupo2.laboratorio1.bda.models.TareaHabilidad;

public interface ITareaHabilidadRepository{
    
    void createTareaHabilidad(TareaHabilidad tareaHabilidad);
    List<TareaHabilidad> getAllTareaHabilidad();
    TareaHabilidad getTareaHabilidad(Integer idTareaHabilidad);
    TareaHabilidad updateTareaHabilidad(Integer idTareaHabilidad, TareaHabilidad tareaHabilidad);
    void deleteTareaHabilidad(Integer idTareaHabilidad);
    List<TareaHabilidad> getTareaHabilidadByIdTarea(Integer idTarea);
    List<TareaHabilidad> getTareaHabilidadByIdHabilidad(Integer idHabilidad);
    List<TareaHabilidad> getTareaHabilidadByIdEmergencia(Integer idEmergencia);
}

