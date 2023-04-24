package grupo2.laboratorio1.bda.repositories;

import grupo2.laboratorio1.bda.models.Eme_Habilidad;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

@Repository
public class Eme_HabilidadRepository implements IEme_HabilidadRepository {
    @Autowired
    private Sql2o sql2o;

    @Override
    public void createEme_Habilidad(Eme_Habilidad eme_habilidad){
        String queryText = "INSERT INTO Eme_Habilidad (id_emergencia, id_habilidad) VALUES (:id_emergencia, :id_habilidad)";
        try( Connection connection = sql2o.open()){
            connection.createQuery(queryText)
                    .addParameter("id_emergencia", eme_habilidad.getIdEmergencia())
                    .addParameter("id_habilidad", eme_habilidad.getIdHabilidad())
                    .executeUpdate();
        }
        catch (Exception e){
            throw new RuntimeException("No se pudo crear la tabla relacion entre emergencia y habilidad");
        }
    }

    public Eme_Habilidad getEme_Habilidad(Integer idEmeHabilidad){
        String queryText = "SELECT * FROM Eme_Habilidad WHERE id_eme_habilidad = :idEmeHabilidad";
        try (Connection connection = sql2o.open()){
            Query query = connection.createQuery(queryText)
                    .addParameter("idEmeHabilidad", idEmeHabilidad)
                    .addColumnMapping("ID_EME_HABILIDAD", "idEmeHabilidad")
                    .addColumnMapping("ID_EMERGENCIA", "idEmergencia")
                    .addColumnMapping("ID_HABILIDAD", "idHabilidad");
            Eme_Habilidad eme_habilidad = query.executeAndFetchFirst(Eme_Habilidad.class);
            return eme_habilidad;

        }
        catch (Exception e){
            throw new RuntimeException("No se pudo obtener la tabla relacion entre emergencia y habilidad");
        }
    }

    public List<Eme_Habilidad> getAllEme_Habilidad(){
        String queryText = "SELECT * FROM Eme_Habilidad";
        try (Connection connection = sql2o.open()){
            Query query = connection.createQuery(queryText)
                    .addColumnMapping("ID_EME_HABILIDAD", "idEmeHabilidad")
                    .addColumnMapping("ID_EMERGENCIA", "idEmergencia")
                    .addColumnMapping("ID_HABILIDAD", "idHabilidad");
            List<Eme_Habilidad> eme_habilidad = query.executeAndFetch(Eme_Habilidad.class);
            return eme_habilidad;

        }
        catch (Exception e){
            throw new RuntimeException("No se pudo obtener la tabla relacion entre emergencia y habilidad");
        }
    }

    public Eme_Habilidad updateEme_Habilidad(Integer idEmeHabilidad, Eme_Habilidad eme_Habilidad){
        String queryText = "UPDATE Eme_Habilidad SET id_emergencia = :id_emergencia, id_habilidad = :id_habilidad WHERE id_eme_habilidad = :id_eme_habilidad";
        try (Connection connection = sql2o.open()){
            Query query = connection.createQuery(queryText)
                    .addParameter("id_emergencia", eme_Habilidad.getIdEmergencia())
                    .addParameter("id_habilidad", eme_Habilidad.getIdHabilidad())
                    .addParameter("id_eme_habilidad", idEmeHabilidad);
            Eme_Habilidad eme_habilidad = query.executeAndFetchFirst(Eme_Habilidad.class);
            return eme_habilidad;

        }
        catch (Exception e){
            throw new RuntimeException("No se pudo actualizar la tabla relacion entre emergencia y habilidad");
        }
    }
}
