package grupo2.laboratorio1.bda.repositories;

import grupo2.laboratorio1.bda.models.Estado_Tarea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class Estado_TareaRepository implements IEstado_TareaRepository{
    @Autowired
    private Sql2o sql2o;

    @Override
    public void createEstado_Tarea(Estado_Tarea estado_tarea) {
        String queryText = "INSERT INTO ranking (id_estado_tarea, descripcion) " +
                "VALUES (:idEstado_Tarea, :descripcion)";

        try(Connection connection = sql2o.open()){
            Query query = connection.createQuery(queryText)
                    .addParameter("idEstado_Tarea", estado_tarea.getIdEstado_Tarea())
                    .addParameter("descripcion", estado_tarea.getDescripcion());
            query.executeUpdate();
        }
        catch (Exception e){
            throw new RuntimeException("Ocurrio un error al registrar el estado de la tarea");
        }
    }

    @Override
    public Estado_Tarea getEstado_Tarea(Integer idEstado_Tarea) {
        String queryText = "SELECT id_estado_tarea, descripcion FROM estado_tarea WHERE id_estado_tarea = :idEstado_Tarea";

        try(Connection connection = sql2o.open()){
            Query query = connection.createQuery(queryText)
                    .addParameter("idEstado_Tarea", idEstado_Tarea)
                    .addColumnMapping("ID_ESTADO_TAREA", "idEstado_Tarea");
            Estado_Tarea estado_tarea = query.executeAndFetchFirst(Estado_Tarea.class);
            return estado_tarea;
        }
        catch (Exception e){
            throw new RuntimeException("Ocurrio un error al obtener el estado de la tarea");
        }
    }

    @Override
    public List<Estado_Tarea> getAllEstado_Tareas() {
        String queryText = "SELECT id_estado_tarea, descripcion FROM estado_tarea";

        try(Connection connection = sql2o.open()){
            Query query = connection.createQuery(queryText)
                    .addColumnMapping("ID_ESTADO_TAREA", "idEstado_Tarea");
            List<Estado_Tarea> estados_tareas = query.executeAndFetch(Estado_Tarea.class);
            return estados_tareas;
        }
        catch (Exception e){
            throw new RuntimeException("Ocurrio un error al obtener los estados de las tarea");
        }
    }

    @Override
    public void updateEstado_Tarea(Estado_Tarea habilidad) {
        String queryText = "UPDATE habilidad SET " +
                "nombre = COALESCE(:descripcion, descripcion), " +
                "WHERE id_estado_tarea = :idEstado_Tarea";

        try(Connection connection = sql2o.open()){
            Query query = connection.createQuery(queryText)
                    .addParameter("descripcion", habilidad.getDescripcion())
                    .addParameter("idEstado_Tarea", habilidad.getIdEstado_Tarea());
            query.executeUpdate();
        }
        catch (Exception e){
            throw new RuntimeException("Ocurrio un error al actualizar el estado de la tarea");
        }
    }

    @Override
    public void deleteEstado_Tarea(Integer idEstado_Tarea) {
        String queryText = "DELETE FROM estado_tarea WHERE id_estado_tarea = :idEstado_Tarea";

        try(Connection connection = sql2o.open()){
            Query query = connection.createQuery(queryText)
                    .addParameter("idEstado_Tarea", idEstado_Tarea);
            query.executeUpdate();
        }
        catch (Exception e){
            throw new RuntimeException("Ocurrio un error al eliminar el estado de la tarea");
        }
    }
}