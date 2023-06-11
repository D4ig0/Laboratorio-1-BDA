package grupo2.laboratorio1.bda.repositories;

import grupo2.laboratorio1.bda.models.Tarea;

import java.util.List;

import grupo2.laboratorio1.bda.models.Voluntario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;



@Repository
public class TareaRepository implements ITareaRepository{
    @Autowired
    private Sql2o sql2o;

    @Override
    public void createTarea(Tarea tarea){
        String queryText = "INSERT INTO tarea (id_emergencia, nombre, descripcion, cant_vol_requeridos, cant_vol_inscritos, fecha_inicio, fecha_fin, estado_actual) VALUES (:idEmergencia, :nombre, :descripcion, :cantVolRequeridos, :cantVolInscritos, :fechaInicio, :fechaFin, :estadoActual)";
        try(Connection connection = sql2o.open()){
            Query query = connection.createQuery(queryText)
                    .addParameter("idEmergencia", tarea.getIdEmergencia())
                    .addParameter("nombre", tarea.getNombre())
                    .addParameter("descripcion", tarea.getDescripcion())
                    .addParameter("cantVolRequeridos", tarea.getCantVolRequeridos())
                    .addParameter("cantVolInscritos", tarea.getCantVolInscritos())
                    .addParameter("fechaInicio", tarea.getFechaInicio())
                    .addParameter("fechaFin", tarea.getFechaFin())
                    .addParameter("estadoActual", tarea.getEstadoActual());
            query.executeUpdate();
        }
        catch (Exception e){
            throw new RuntimeException("Ocurrio un error al crear la tarea");
        }
    }
    
    @Override
    public Tarea getTarea(Integer idTarea) {
        String queryText = "SELECT * FROM tarea WHERE id_tarea = :idTarea";

        try(Connection connection = sql2o.open()){
            Query query = connection.createQuery(queryText)
                    .addParameter("idTarea", idTarea)
                    .addColumnMapping("id_tarea", "idTarea")
                    .addColumnMapping("id_emergencia", "idEmergencia")
                    .addColumnMapping("nombre", "nombre")
                    .addColumnMapping("descripcion", "descripcion")
                    .addColumnMapping("cant_vol_requeridos", "cantVolRequeridos")
                    .addColumnMapping("cant_vol_inscritos", "cantVolInscritos")
                    .addColumnMapping("fecha_inicio", "fechaInicio")
                    .addColumnMapping("fecha_fin", "fechaFin")
                    .addColumnMapping("estado_actual", "estadoActual");
            Tarea tarea = query.executeAndFetchFirst(Tarea.class);
            return tarea;
        }
        catch (Exception e){
            throw new RuntimeException("Ocurrio un error al obtener la tarea");
        }
    }

    @Override
    public List<Tarea> getAllTareas() {
        String queryText = "SELECT * FROM tarea";

        try(Connection connection = sql2o.open()){
            Query query = connection.createQuery(queryText)
        
            .addColumnMapping("id_tarea", "idTarea")
            .addColumnMapping("id_emergencia", "idEmergencia")
            .addColumnMapping("nombre", "nombre")
            .addColumnMapping("descripcion", "descripcion")
            .addColumnMapping("cant_vol_requeridos", "cantVolRequeridos")
            .addColumnMapping("cant_vol_inscritos", "cantVolInscritos")
            .addColumnMapping("fecha_inicio", "fechaInicio")
            .addColumnMapping("fecha_fin", "fechaFin")
            .addColumnMapping("estado_actual", "estadoActual");
            List<Tarea> tareas = query.executeAndFetch(Tarea.class);
            return tareas;}

        catch (Exception e){
            throw new RuntimeException("Ocurrio un error al obtener todas las tareas");
        }
    }

    @Override
    public void updateTarea(Tarea tarea) {
        String queryText = "UPDATE tarea SET id_emergencia = coalesce(:idEmergencia, id_emergencia), nombre = coalesce(:nombre, nombre), descripcion = coalesce(:descripcion, descripcion), cant_vol_requeridos = coalesce(:cantVolRequeridos, cant_vol_requeridos), cant_vol_inscritos = coalesce(:cantVolInscritos, cant_vol_inscritos), fecha_inicio = coalesce(:fechaInicio, fecha_inicio), fecha_fin = coalesce(:fechaFin, fecha_fin), estado_actual = coalesce(:estadoActual, estado_actual) WHERE id_tarea = :idTarea";
        try(Connection connection = sql2o.open()){
            Query query = connection.createQuery(queryText)
                    .addParameter("idEmergencia", tarea.getIdEmergencia())
                    .addParameter("nombre", tarea.getNombre())
                    .addParameter("descripcion", tarea.getDescripcion())
                    .addParameter("cantVolRequeridos", tarea.getCantVolRequeridos())
                    .addParameter("cantVolInscritos", tarea.getCantVolInscritos())
                    .addParameter("fechaInicio", tarea.getFechaInicio())
                    .addParameter("fechaFin", tarea.getFechaFin())
                    .addParameter("estadoActual", tarea.getEstadoActual())
                    .addParameter("idTarea", tarea.getIdTarea());
            query.executeUpdate();
        }
        catch (Exception e){
            throw new RuntimeException("Ocurrio un error al actualizar la tarea");
        }
    }

    public boolean existsTarea(Integer idTarea){
        String queryText = "SELECT EXISTS(SELECT * FROM tarea WHERE id_tarea = :idTarea)";

        try(Connection connection = sql2o.open()){
            Query query = connection.createQuery(queryText)
                    .addParameter("idTarea", idTarea);
            boolean exists = query.executeAndFetchFirst(Boolean.class);
            return exists;
        }
        catch (Exception e){
            throw new RuntimeException("Ocurrio un error al realizar la query");
        }
    }

    @Override
    public void deleteTarea(Integer idTarea) {
        String queryText = "DELETE FROM tarea WHERE id_tarea = :idTarea";

        try(Connection connection = sql2o.open()){
            Query query = connection.createQuery(queryText)
                    .addParameter("idTarea", idTarea);
            query.executeUpdate();
        }
        catch (Exception e){
            throw new RuntimeException("Ocurrio un error al eliminar la tarea");
        }
    }

    @Override
    public Integer getTotalTareasByEmergencia(Integer idEmergencia){
        String queryText = "SELECT total_tareas_activas_por_emergencia(:idEmergencia)";

        try(Connection connection = sql2o.open()){
            Query query = connection.createQuery(queryText)
                    .addParameter("idEmergencia", idEmergencia);
            return query.executeScalar(Integer.class);
        }
        catch (Exception e){
            throw new RuntimeException("Ocurrio un error al buscar las tareas activas");
        }
    }

    @Override
    public List<Tarea> getTareasEnRegion(Integer idRegion) {
        String queryText = "SELECT t.*, ST_X(e.ubicacion) AS latitud, ST_Y(e.ubicacion) AS longitud " +
                "FROM tarea t, division_regional r, emergencia e " +
                "WHERE e.id_emergencia = t.id_emergencia " +
                "AND r.gid = :idRegion " +
                "AND ST_CONTAINS(r.geom, e.ubicacion)";
        try (Connection connection = sql2o.open()) {
            List<Tarea> tareas = connection.createQuery(queryText)
                    .addParameter("idRegion", idRegion)
                    .addColumnMapping("id_tarea", "idTarea")
                    .addColumnMapping("id_emergencia", "idEmergencia")
                    .addColumnMapping("nombre", "nombre")
                    .addColumnMapping("descripcion", "descripcion")
                    .addColumnMapping("cant_vol_requeridos", "cantVolRequeridos")
                    .addColumnMapping("cant_vol_inscritos", "cantVolInscritos")
                    .addColumnMapping("fecha_inicio", "fechaInicio")
                    .addColumnMapping("fecha_fin", "fechaFin")
                    .addColumnMapping("estado_actual", "estadoActual")
                    .addColumnMapping("latitud", "latitud")
                    .addColumnMapping("longitud", "longitud")
                    .executeAndFetch(Tarea.class);
            return tareas;
        } catch (Exception e) {
            throw new RuntimeException("Ocurrió un error al obtener las tareas", e);
        }
    }



}







    


