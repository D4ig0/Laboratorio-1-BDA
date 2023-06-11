package grupo2.laboratorio1.bda.repositories;

import grupo2.laboratorio1.bda.models.TareaHabilidad;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Query;


@Repository
public class TareaHabilidadRepository implements ITareaHabilidadRepository{
    @Autowired
    private Sql2o sql2o;

    @Override
    public void createTareaHabilidad(TareaHabilidad tareaHabilidad){
        String queryText = "INSERT INTO tarea_habilidad (id_habilidad, id_tarea) VALUES (:id_habilidad, :id_tarea)";
        try (Connection connection = sql2o.open()){
            Query query = connection.createQuery(queryText)
                    .addParameter("id_habilidad", tareaHabilidad.getIdHabilidad())
                    .addParameter("id_tarea", tareaHabilidad.getIdTarea());
            query.executeUpdate();
        }
        catch (Exception e){
            throw new RuntimeException("No se pudo registrar la tabla relacion entre tarea y habilidad");
        }
    }

    @Override
    public TareaHabilidad getTareaHabilidad(Integer idTareaHabilidad){
        String queryText = "SELECT * FROM tarea_habilidad WHERE id_tarea_habilidad = :idTareaHabilidad";
        try (Connection connection = sql2o.open()){
            Query query = connection.createQuery(queryText)
                    .addParameter("idTareaHabilidad", idTareaHabilidad)
                    .addColumnMapping("ID_TAREA_HABILIDAD", "idTareaHabilidad")
                    .addColumnMapping("ID_EMERGENCIA", "idEmergencia")
                    .addColumnMapping("ID_HABILIDAD", "idHabilidad")
                    .addColumnMapping("ID_TAREA", "idTarea");
            TareaHabilidad tareaHabilidad = query.executeAndFetchFirst(TareaHabilidad.class);
            return tareaHabilidad;
        }
        catch (Exception e){
            throw new RuntimeException("No se pudo obtener la tabla relacion entre tarea y habilidad");
        }
    }

    @Override
    public List<TareaHabilidad> getAllTareaHabilidad(){
        String queryText = "SELECT * FROM tarea_habilidad";
        try (Connection connection = sql2o.open()){
            Query query = connection.createQuery(queryText)
                    .addColumnMapping("ID_TAREA_HABILIDAD", "idTareaHabilidad")
                    .addColumnMapping("ID_EMERGENCIA", "idEmergencia")
                    .addColumnMapping("ID_HABILIDAD", "idHabilidad")
                    .addColumnMapping("ID_TAREA", "idTarea");
            List<TareaHabilidad> tareaHabilidad = query.executeAndFetch(TareaHabilidad.class);
            return tareaHabilidad;

        }
        catch (Exception e){
            throw new RuntimeException("No se pudo obtener la tabla relacion entre tarea y habilidad");
        }}

    @Override
    public TareaHabilidad updateTareaHabilidad(Integer idTareaHabilidad, TareaHabilidad tareaHabilidad){
        String queryText = "UPDATE tarea_habilidad SET id_habilidad = coalesce(:idHabilidad, id_habilidad), id_tarea = coalesce(:idTarea, id_tarea) WHERE id_tarea_habilidad = :idTareaHabilidad";
        try (Connection connection = sql2o.open()){
            Query query = connection.createQuery(queryText)
                    .addParameter("idHabilidad", tareaHabilidad.getIdHabilidad())
                    .addParameter("idTarea", tareaHabilidad.getIdTarea())
                    .addParameter("idTareaHabilidad", idTareaHabilidad);
            query.executeUpdate();
            TareaHabilidad tareaHabilidadActualizada = this.getTareaHabilidad(idTareaHabilidad);
            return tareaHabilidadActualizada;
        }
        catch (Exception e){
            throw new RuntimeException("No se pudo actualizar la tabla relacion entre tarea y habilidad");
        }
    }

    @Override
    public void deleteTareaHabilidad(Integer idTareaHabilidad){
        String queryText = "DELETE FROM tarea_habilidad WHERE id_tarea_habilidad = :idTareaHabilidad";
        try (Connection connection = sql2o.open()){
            Query query = connection.createQuery(queryText)
                    .addParameter("idTareaHabilidad", idTareaHabilidad);
            query.executeUpdate();
        }
        catch (Exception e){
            throw new RuntimeException("No se pudo eliminar la tabla relacion entre tarea y habilidad");
        }
    }

    @Override
    public List<TareaHabilidad> getTareaHabilidadByIdHabilidad(Integer idHabilidad){
        String queryText = "SELECT * FROM tarea_habilidad WHERE id_habilidad = :idHabilidad";
        try (Connection connection = sql2o.open()){
            Query query = connection.createQuery(queryText)
                    .addParameter("idHabilidad", idHabilidad)
                    .addColumnMapping("ID_TAREA_HABILIDAD", "idTareaHabilidad")
                    .addColumnMapping("ID_EMERGENCIA", "idEmergencia")
                    .addColumnMapping("ID_HABILIDAD", "idHabilidad")
                    .addColumnMapping("ID_TAREA", "idTarea");
            List<TareaHabilidad> tareaHabilidad = query.executeAndFetch(TareaHabilidad.class);
            return tareaHabilidad;

        }
        catch (Exception e){
            throw new RuntimeException("No se pudo obtener la tabla relacion entre tarea y habilidad");
        }
    }
    @Override
    public List<TareaHabilidad> getTareaHabilidadByIdTarea(Integer idTarea){
        String queryText = "SELECT * FROM tarea_habilidad WHERE id_tarea = :idTarea";
        try (Connection connection = sql2o.open()){
            Query query = connection.createQuery(queryText)
                    .addParameter("idTarea", idTarea)
                    .addColumnMapping("ID_TAREA_HABILIDAD", "idTareaHabilidad")
                    .addColumnMapping("ID_EMERGENCIA", "idEmergencia")
                    .addColumnMapping("ID_HABILIDAD", "idHabilidad")
                    .addColumnMapping("ID_TAREA", "idTarea");
            List<TareaHabilidad> tareaHabilidad = query.executeAndFetch(TareaHabilidad.class);
            return tareaHabilidad;

        }
        catch (Exception e){
            throw new RuntimeException("No se pudo obtener la tabla relacion entre tarea y habilidad");
        }
    }

    @Override
    public List<TareaHabilidad> getTareaHabilidadByIdEmergencia(Integer idEmergencia){
        String queryText = "SELECT * FROM tarea_habilidad WHERE id_emergencia = :idEmergencia";
        try (Connection connection = sql2o.open()){
            Query query = connection.createQuery(queryText)
                    .addParameter("idEmergencia", idEmergencia)
                    .addColumnMapping("ID_TAREA_HABILIDAD", "idTareaHabilidad")
                    .addColumnMapping("ID_EMERGENCIA", "idEmergencia")
                    .addColumnMapping("ID_HABILIDAD", "idHabilidad")
                    .addColumnMapping("ID_TAREA", "idTarea");
            List<TareaHabilidad> tareaHabilidad = query.executeAndFetch(TareaHabilidad.class);
            return tareaHabilidad;

        }
        catch (Exception e){
            throw new RuntimeException("No se pudo obtener la tabla relacion entre tarea y habilidad");
        }
    }      
}


