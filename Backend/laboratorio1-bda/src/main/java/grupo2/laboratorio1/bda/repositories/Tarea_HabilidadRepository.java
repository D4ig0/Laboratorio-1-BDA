package grupo2.laboratorio1.bda.repositories;

import grupo2.laboratorio1.bda.models.Tarea_Habilidad;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Query;


@Repository
public class Tarea_HabilidadRepository implements ITarea_HabilidadRepository {
    @Autowired
    private Sql2o sql2o;

    @Override
    public void createTarea_Habilidad(Tarea_Habilidad tarea_Habilidad){
        String queryText = "INSERT INTO Tarea_Habilidad (id_emergencia, id_habilidad, id_tarea) VALUES (:id_emergencia, :id_habilidad, :id_tarea)";
        try( Connection Connection = sql2o.open()){
            Connection.createQuery(queryText)
                    .addParameter("id_emergencia", tarea_Habilidad.getIdEmergencia())
                    .addParameter("id_habilidad", tarea_Habilidad.getIdHabilidad())
                    .addParameter("id_habilidad", tarea_Habilidad.getIdTarea())
                    .executeUpdate();
        }
        catch (Exception e){
            throw new RuntimeException("No se pudo crear la tabla relacion entre tarea y habilidad");
        }
    }

    
    public Tarea_Habilidad getTarea_Habilidad(Integer idTareaHabilidad){
        String queryText = "SELECT * FROM Tarea_Habilidad WHERE id_tarea_habilidad = :idTareaHabilidad";
        try (Connection connection = sql2o.open()){
            Query query = connection.createQuery(queryText)
                    .addParameter("idTareaHabilidad", idTareaHabilidad)
                    .addColumnMapping("ID_TAREA_HABILIDAD", "idTareaHabilidad")
                    .addColumnMapping("ID_EMERGENCIA", "idEmergencia")
                    .addColumnMapping("ID_HABILIDAD", "idHabilidad")
                    .addColumnMapping("ID_TAREA", "idTarea");
            Tarea_Habilidad tarea_Habilidad = query.executeAndFetchFirst(Tarea_Habilidad.class);
            return tarea_Habilidad;
        }
        catch (Exception e){
            throw new RuntimeException("No se pudo obtener la tabla relacion entre tarea y habilidad");
        }
    }

 
    public List<Tarea_Habilidad> getAllTarea_Habilidad(){
        String queryText = "SELECT * FROM Tarea_Habilidad";
        try (Connection connection = sql2o.open()){
            Query query = connection.createQuery(queryText)
                    .addColumnMapping("ID_TAREA_HABILIDAD", "idTareaHabilidad")
                    .addColumnMapping("ID_EMERGENCIA", "idEmergencia")
                    .addColumnMapping("ID_HABILIDAD", "idHabilidad")
                    .addColumnMapping("ID_TAREA", "idTarea");
            List<Tarea_Habilidad> tarea_Habilidad = query.executeAndFetch(Tarea_Habilidad.class);
            return tarea_Habilidad;

        }
        catch (Exception e){
            throw new RuntimeException("No se pudo obtener la tabla relacion entre tarea y habilidad");
        }}

        public Tarea_Habilidad updateTarea_Habilidad(Integer idTareaHabilidad, Tarea_Habilidad tarea_Habilidad){
            String queryText = "UPDATE Tarea_Habilidad SET id_emergencia = :id_emergencia, id_habilidad = :id_habilidad, id_tarea = :id_tarea WHERE id_tarea_habilidad = :id_tarea_habilidad";
            try (Connection connection = sql2o.open()){
                Query query = connection.createQuery(queryText)
                        .addParameter("id_emergencia", tarea_Habilidad.getIdEmergencia())
                        .addParameter("id_habilidad", tarea_Habilidad.getIdHabilidad())
                        .addParameter("id_tarea", tarea_Habilidad.getIdTarea())
                        .addParameter("id_tarea_habilidad", idTareaHabilidad);
                Tarea_Habilidad tarea_habilidad = query.executeAndFetchFirst(Tarea_Habilidad.class);
                return tarea_habilidad;
    
            }
            catch (Exception e){
                throw new RuntimeException("No se pudo actualizar la tabla relacion entre tarea y habilidad");
            }
        }

    

    }



