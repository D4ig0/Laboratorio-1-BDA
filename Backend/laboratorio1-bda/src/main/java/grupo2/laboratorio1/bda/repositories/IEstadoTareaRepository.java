package grupo2.laboratorio1.bda.repositories;

import grupo2.laboratorio1.bda.models.EstadoTarea;

import java.util.List;

public interface IEstadoTareaRepository {
    void createEstadoTarea(EstadoTarea estadoTarea);
    EstadoTarea getEstadoTarea(Integer idEstadoTarea);
    List<EstadoTarea> getAllEstadoTareas();
    void updateEstadoTarea(EstadoTarea estadoTarea);
    void deleteEstadoTarea(Integer idEstadoTarea);
}
