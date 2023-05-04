package grupo2.laboratorio1.bda.repositories;

import grupo2.laboratorio1.bda.models.EmeHabilidad;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

@Repository
public class EmeHabilidadRepository implements IEmeHabilidadRepository {
    @Autowired
    private Sql2o sql2o;

    @Override
    public void createEmeHabilidad(EmeHabilidad emeHabilidad){
        String queryText = "INSERT INTO eme_habilidad (id_emergencia, id_habilidad) VALUES (:id_emergencia, :id_habilidad)";
        try( Connection connection = sql2o.open()){
            connection.createQuery(queryText)
                    .addParameter("id_emergencia", emeHabilidad.getIdEmergencia())
                    .addParameter("id_habilidad", emeHabilidad.getIdHabilidad())
                    .executeUpdate();
        }
        catch (Exception e){
            throw new RuntimeException("No se pudo crear la tabla relacion entre emergencia y habilidad");
        }
    }

    public EmeHabilidad getEmeHabilidad(Integer idEmeHabilidad){
        String queryText = "SELECT * FROM eme_habilidad WHERE id_eme_habilidad = :idEmeHabilidad";
        try (Connection connection = sql2o.open()){
            Query query = connection.createQuery(queryText)
                    .addParameter("idEmeHabilidad", idEmeHabilidad)
                    .addColumnMapping("ID_EME_HABILIDAD", "idEmeHabilidad")
                    .addColumnMapping("ID_EMERGENCIA", "idEmergencia")
                    .addColumnMapping("ID_HABILIDAD", "idHabilidad");
            EmeHabilidad emeHabilidad = query.executeAndFetchFirst(EmeHabilidad.class);
            return emeHabilidad;

        }
        catch (Exception e){
            throw new RuntimeException("No se pudo obtener la tabla relacion entre emergencia y habilidad");
        }
    }

    public List<EmeHabilidad> getAllEmeHabilidad(){
        String queryText = "SELECT * FROM eme_habilidad";
        try (Connection connection = sql2o.open()){
            Query query = connection.createQuery(queryText)
                    .addColumnMapping("ID_EME_HABILIDAD", "idEmeHabilidad")
                    .addColumnMapping("ID_EMERGENCIA", "idEmergencia")
                    .addColumnMapping("ID_HABILIDAD", "idHabilidad");
            List<EmeHabilidad> emeHabilidad = query.executeAndFetch(EmeHabilidad.class);
            return emeHabilidad;

        }
        catch (Exception e){
            throw new RuntimeException("No se pudo obtener la tabla relacion entre emergencia y habilidad");
        }
    }

    public EmeHabilidad updateEmeHabilidad(Integer idEmeHabilidad, EmeHabilidad emeHabilidad){
        String queryText = "UPDATE eme_habilidad SET id_emergencia = coalesce(:idEmergencia, id_emergencia), id_habilidad = coalesce(:idHabilidad, id_habilidad) WHERE id_eme_habilidad = :idEmeHabilidad";
        try (Connection connection = sql2o.open()){
            connection.createQuery(queryText)
                    .addParameter("idEmeHabilidad", idEmeHabilidad)
                    .addParameter("idEmergencia", emeHabilidad.getIdEmergencia())
                    .addParameter("idHabilidad", emeHabilidad.getIdHabilidad())
                    .executeUpdate();
            return getEmeHabilidad(idEmeHabilidad);
        }
        catch (Exception e){
            throw new RuntimeException("No se pudo actualizar la tabla relacion entre emergencia y habilidad");
        }
    }

    public void deleteEmeHabilidad(Integer idEmeHabilidad){
        String queryText = "DELETE FROM eme_habilidad WHERE id_eme_habilidad = :idEmeHabilidad";
        try (Connection connection = sql2o.open()){
            Query query = connection.createQuery(queryText)
                    .addParameter("idEmeHabilidad", idEmeHabilidad);
            query.executeUpdate();
        }
        catch (Exception e){
            throw new RuntimeException("No se pudo eliminar la tabla relacion entre emergencia y habilidad");
        }
    }

    public List<EmeHabilidad> getEmeHabilidadByIdEmergencia(Integer idEmergencia){
        String queryText = "SELECT * FROM eme_habilidad WHERE id_emergencia = :idEmergencia";
        try (Connection connection = sql2o.open()){
            Query query = connection.createQuery(queryText)
                    .addParameter("idEmergencia", idEmergencia)
                    .addColumnMapping("ID_EME_HABILIDAD", "idEmeHabilidad")
                    .addColumnMapping("ID_EMERGENCIA", "idEmergencia")
                    .addColumnMapping("ID_HABILIDAD", "idHabilidad");
            List<EmeHabilidad> emeHabilidad = query.executeAndFetch(EmeHabilidad.class);
            return emeHabilidad;

        }
        catch (Exception e){
            throw new RuntimeException("No se pudo obtener la tabla relacion entre emergencia y habilidad");
        }
    }

    public List<EmeHabilidad> getEmeHabilidadByIdHabilidad(Integer idHabilidad){
        String queryText = "SELECT * FROM eme_habilidad WHERE id_habilidad = :idHabilidad";
        try (Connection connection = sql2o.open()){
            Query query = connection.createQuery(queryText)
                    .addParameter("idHabilidad", idHabilidad)
                    .addColumnMapping("ID_EME_HABILIDAD", "idEmeHabilidad")
                    .addColumnMapping("ID_EMERGENCIA", "idEmergencia")
                    .addColumnMapping("ID_HABILIDAD", "idHabilidad");
            List<EmeHabilidad> emeHabilidad = query.executeAndFetch(EmeHabilidad.class);
            return emeHabilidad;

        }
        catch (Exception e){
            throw new RuntimeException("No se pudo obtener la tabla relacion entre emergencia y habilidad");
        }
    }
}
