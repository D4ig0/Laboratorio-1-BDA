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
        String queryText = "INSERT INTO tarea_habilidad (id_emergencia, id_habilidad, id_tarea) VALUES (:id_emergencia, :id_habilidad, :id_tarea)";
        try( Connection Connection = sql2o.open()){
            Connection.createQuery(queryText)
                    .addParameter("id_emergencia", tareaHabilidad.getIdEmergencia())
                    .addParameter("id_habilidad", tareaHabilidad.getIdHabilidad())
                    .addParameter("id_habilidad", tareaHabilidad.getIdTarea())
                    .executeUpdate();
        }
        catch (Exception e){
            throw new RuntimeException("No se pudo crear la tabla relacion entre tarea y habilidad");
        }
    }

    
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

        public TareaHabilidad updateTareaHabilidad(Integer idTareaHabilidad, TareaHabilidad tareaHabilidad){
            String queryText = "UPDATE tarea_habilidad SET id_emergencia = :id_emergencia, id_habilidad = :id_habilidad, id_tarea = :id_tarea WHERE id_tarea_habilidad = :id_tarea_habilidad";
            try (Connection connection = sql2o.open()){
                Query query = connection.createQuery(queryText)
                        .addParameter("id_emergencia", tareaHabilidad.getIdEmergencia())
                        .addParameter("id_habilidad", tareaHabilidad.getIdHabilidad())
                        .addParameter("id_tarea", tareaHabilidad.getIdTarea())
                        .addParameter("id_tarea_habilidad", idTareaHabilidad);
                TareaHabilidad tarea_habilidad = query.executeAndFetchFirst(TareaHabilidad.class);
                return tarea_habilidad;
    
            }
            catch (Exception e){
                throw new RuntimeException("No se pudo actualizar la tabla relacion entre tarea y habilidad");
            }
        }
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
    }


