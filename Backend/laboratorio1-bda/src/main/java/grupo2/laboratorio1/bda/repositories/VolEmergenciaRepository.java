package grupo2.laboratorio1.bda.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import grupo2.laboratorio1.bda.models.VolEmergencia;

@Repository
public class VolEmergenciaRepository implements IVolEmergenciaRepository {
    @Autowired
    private Sql2o sql2o;

    @Override
    public void createVolEmergencia(VolEmergencia volEmergencia) {
        String query = "INSERT INTO vol_emergencia (idVoluntario, idEmergencia) VALUES (:idVoluntario, :idEmergencia)";
        try (org.sql2o.Connection con = sql2o.open()) {
            con.createQuery(query)
                    .addParameter("idVoluntario", volEmergencia.getIdVoluntario())
                    .addParameter("idEmergencia", volEmergencia.getIdEmergencia())
                    .executeUpdate();
        } catch(Exception e){
            throw new RuntimeException("No se pudo crear la relación entre el voluntario y la emergencia");
        }
    }

    public VolEmergencia getVolEmergencia(Integer idVolEmergencia){
        String queryTxt = "SELECT * FROM vol_emergencia WHERE idVolEmergencia = :idVolEmergencia";
        try (Connection connection = sql2o.open()) {
            Query query = connection.createQuery(queryTxt)
                    .addParameter("idVolEmergencia", idVolEmergencia)
                    .addColumnMapping("ID_VOL_EMERGENCIA", "idVolEmergencia")
                    .addColumnMapping("ID_VOLUNTARIO", "idVoluntario")
                    .addColumnMapping("ID_EMERGENCIA", "idEmergencia");
            VolEmergencia volEmergencia = query.executeAndFetchFirst(VolEmergencia.class);
            return volEmergencia;

        } catch(Exception e){
            throw new RuntimeException("No se pudo obtener la relación entre el voluntario y la emergencia");
        }
    }

    public List<VolEmergencia> getAllVolEmergencia(){
        String queryTxt = "SELECT * FROM vol_emergencia";
        try (Connection connection = sql2o.open()) {
            Query query = connection.createQuery(queryTxt)
                    .addColumnMapping("ID_VOL_EMERGENCIA", "idVolEmergencia")
                    .addColumnMapping("ID_VOLUNTARIO", "idVoluntario")
                    .addColumnMapping("ID_EMERGENCIA", "idEmergencia");
            List<VolEmergencia> volEmergencia = query.executeAndFetch(VolEmergencia.class);
            return volEmergencia;

        } catch(Exception e){
            throw new RuntimeException("No se pudo obtener la relación entre el voluntario y la emergencia");
        }
    }

    public VolEmergencia updateVolEmergencia(Integer idVolEmergencia, VolEmergencia volEmergencia){
        String queryTxt = "UPDATE vol_emergencia SET idVoluntario = :idVoluntario, idEmergencia = :idEmergencia WHERE id_vol_emergencia = :idVolEmergencia";
        try (Connection connection = sql2o.open()) {
            Query query = connection.createQuery(queryTxt)
                    .addParameter("idVolEmergencia", idVolEmergencia)
                    .addParameter("idVoluntario", volEmergencia.getIdVoluntario())
                    .addParameter("idEmergencia", volEmergencia.getIdEmergencia())
                    .addColumnMapping("ID_VOL_EMERGENCIA", "idVolEmergencia")
                    .addColumnMapping("ID_VOLUNTARIO", "idVoluntario")
                    .addColumnMapping("ID_EMERGENCIA", "idEmergencia");
            VolEmergencia volEmergencia1 = query.executeAndFetchFirst(VolEmergencia.class);
            return volEmergencia1;

        } catch(Exception e){
            throw new RuntimeException("No se pudo actualizar la relación entre el voluntario y la emergencia");
        }

    }

    public void deleteVolEmergencia(Integer idVolEmergencia){
        String queryTxt = "DELETE FROM vol_emergencia WHERE id_vol_emergencia = :idVolEmergencia";
        try (Connection connection = sql2o.open()) {
            Query query = connection.createQuery(queryTxt)
                    .addParameter("idVolEmergencia", idVolEmergencia);
            query.executeUpdate();

        } catch(Exception e){
            throw new RuntimeException("No se pudo eliminar la relación entre el voluntario y la emergencia");
        }
    }

    public List<VolEmergencia> getVolEmergenciaByVoluntario(Integer id_voluntario){
        String queryTxt = "SELECT * FROM vol_emergencia WHERE id_voluntario = :id_voluntario";
        try (Connection connection = sql2o.open()) {
            Query query = connection.createQuery(queryTxt)
                    .addParameter("id_voluntario", id_voluntario)
                    .addColumnMapping("ID_VOL_EMERGENCIA", "idVolEmergencia")
                    .addColumnMapping("ID_VOLUNTARIO", "idVoluntario")
                    .addColumnMapping("ID_EMERGENCIA", "idEmergencia");
            List<VolEmergencia> volEmergencia = query.executeAndFetch(VolEmergencia.class);
            return volEmergencia;

        } catch(Exception e){
            throw new RuntimeException("No se pudo obtener la relación entre el voluntario y la emergencia");
        }    
    }

    public List<VolEmergencia> getVolEmergenciaByEmergencia(Integer id_emergencia){
        String queryTxt = "SELECT * FROM vol_emergencia WHERE id_emergencia = :id_emergencia";
        try (Connection connection = sql2o.open()) {
            Query query = connection.createQuery(queryTxt)
                    .addParameter("id_emergencia", id_emergencia)
                    .addColumnMapping("ID_VOL_EMERGENCIA", "idVolEmergencia")
                    .addColumnMapping("ID_VOLUNTARIO", "idVoluntario")
                    .addColumnMapping("ID_EMERGENCIA", "idEmergencia");
            List<VolEmergencia> volEmergencia = query.executeAndFetch(VolEmergencia.class);
            return volEmergencia;

        } catch(Exception e){
            throw new RuntimeException("No se pudo obtener la relación entre el voluntario y la emergencia");
        }    
    }
}
