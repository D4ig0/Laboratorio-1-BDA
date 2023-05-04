package grupo2.laboratorio1.bda.repositories;

import grupo2.laboratorio1.bda.models.EstadoTarea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;
import java.util.List;


@Repository
public class EstadoTareaRepository implements IEstadoTareaRepository {
    @Autowired
    private Sql2o sql2o;

    @Override
    public void createEstadoTarea(EstadoTarea estadoTarea) {
        String queryText = "INSERT INTO estado_tarea (descripcion) VALUES (:descripcion)";

        try(Connection connection = sql2o.open()){
            Query query = connection.createQuery(queryText)
                    .addParameter("descripcion", estadoTarea.getDescripcion());
            query.executeUpdate();
        }
        catch (Exception e){
            throw new RuntimeException("Ocurrio un error al registrar el estado de la tarea");
        }
    }

    @Override
    public EstadoTarea getEstadoTarea(Integer idEstadoTarea) {
        String queryText = "SELECT id_estado_tarea, descripcion FROM estado_tarea WHERE id_estado_tarea = :idEstadoTarea";

        try(Connection connection = sql2o.open()){
            Query query = connection.createQuery(queryText)
                    .addParameter("idEstadoTarea", idEstadoTarea)
                    .addColumnMapping("ID_ESTADO_TAREA", "idEstadoTarea");
            EstadoTarea estadoTarea = query.executeAndFetchFirst(EstadoTarea.class);
            return estadoTarea;
        }
        catch (Exception e){
            throw new RuntimeException("Ocurrio un error al obtener el estado de la tarea");
        }
    }

    @Override
    public List<EstadoTarea> getAllEstadoTareas() {
        String queryText = "SELECT id_estado_tarea, descripcion FROM estado_tarea";

        try(Connection connection = sql2o.open()){
            Query query = connection.createQuery(queryText)
                    .addColumnMapping("ID_ESTADO_TAREA", "idEstadoTarea");
            List<EstadoTarea> estados_tareas = query.executeAndFetch(EstadoTarea.class);
            return estados_tareas;
        }
        catch (Exception e){
            throw new RuntimeException("Ocurrio un error al obtener los estados de las tarea");
        }
    }

    @Override
    public void updateEstadoTarea(EstadoTarea estadoTarea) {
        String queryText = "UPDATE estado_tarea SET descripcion = coalesce(:descripcion, descripcion) WHERE id_estado_tarea = :idEstadoTarea";
        try(Connection connection = sql2o.open()){
            Query query = connection.createQuery(queryText)
                    .addParameter("descripcion", estadoTarea.getDescripcion())
                    .addParameter("idEstadoTarea", estadoTarea.getIdEstadoTarea());
            query.executeUpdate();
        }
        catch (Exception e){
            throw new RuntimeException("Ocurrio un error al actualizar el estado de la tarea");
        }
    }

    @Override
    public void deleteEstadoTarea(Integer idEstadoTarea) {
        String queryText = "DELETE FROM estado_tarea WHERE id_estado_tarea = :idEstadoTarea";

        try(Connection connection = sql2o.open()){
            Query query = connection.createQuery(queryText)
                    .addParameter("idEstadoTarea", idEstadoTarea);
            query.executeUpdate();
        }
        catch (Exception e){
            throw new RuntimeException("Ocurrio un error al eliminar el estado de la tarea");
        }
    }
}