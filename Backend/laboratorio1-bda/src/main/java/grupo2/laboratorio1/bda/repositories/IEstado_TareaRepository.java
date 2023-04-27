package grupo2.laboratorio1.bda.repositories;

import grupo2.laboratorio1.bda.models.Estado_Tarea;

import java.util.List;

public interface IEstado_TareaRepository {
    void createEstado_Tarea(Estado_Tarea estado_tarea);
    Estado_Tarea getEstado_Tarea(Integer idEstado_Tarea);
    List<Estado_Tarea> getAllEstado_Tareas();
    void updateEstado_Tarea(Estado_Tarea estado_tarea);
    void deleteEstado_Tarea(Integer idEstado_Tarea);
}
