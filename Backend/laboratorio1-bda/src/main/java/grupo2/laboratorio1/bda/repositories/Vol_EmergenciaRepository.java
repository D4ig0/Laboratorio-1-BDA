package grupo2.laboratorio1.bda.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import grupo2.laboratorio1.bda.models.Vol_Emergencia;

@Repository
public class Vol_EmergenciaRepository implements IVol_EmergenciaRepository{
    @Autowired
    private Sql2o sql2o;

    @Override
    public void createVol_Emergencia(Vol_Emergencia vol_Emergencia) {
        String query = "INSERT INTO Vol_Emergencia (idVoluntario, idEmergencia) VALUES (:idVoluntario, :idEmergencia)";
        try (org.sql2o.Connection con = sql2o.open()) {
            con.createQuery(query)
                    .addParameter("idVoluntario", vol_Emergencia.getIdVoluntario())
                    .addParameter("idEmergencia", vol_Emergencia.getIdEmergencia())
                    .executeUpdate();
        } catch(Exception e){
            throw new RuntimeException("No se pudo crear la relación entre el voluntario y la emergencia");
        }
    }

    public Vol_Emergencia getVol_Emergencia(Integer idVolEmergencia){
        String queryTxt = "SELECT * FROM Vol_Emergencia WHERE idVolEmergencia = :idVolEmergencia";
        try (Connection connection = sql2o.open()) {
            Query query = connection.createQuery(queryTxt)
                    .addParameter("idVolEmergencia", idVolEmergencia)
                    .addColumnMapping("ID_VOL_EMERGENCIA", "idVolEmergencia")
                    .addColumnMapping("ID_VOLUNTARIO", "idVoluntario")
                    .addColumnMapping("ID_EMERGENCIA", "idEmergencia");
            Vol_Emergencia vol_Emergencia = query.executeAndFetchFirst(Vol_Emergencia.class);
            return vol_Emergencia;

        } catch(Exception e){
            throw new RuntimeException("No se pudo obtener la relación entre el voluntario y la emergencia");
        }
    }

    public List<Vol_Emergencia> getAllVol_Emergencia(){
        String queryTxt = "SELECT * FROM Vol_Emergencia";
        try (Connection connection = sql2o.open()) {
            Query query = connection.createQuery(queryTxt)
                    .addColumnMapping("ID_VOL_EMERGENCIA", "idVolEmergencia")
                    .addColumnMapping("ID_VOLUNTARIO", "idVoluntario")
                    .addColumnMapping("ID_EMERGENCIA", "idEmergencia");
            List<Vol_Emergencia> vol_Emergencia = query.executeAndFetch(Vol_Emergencia.class);
            return vol_Emergencia;

        } catch(Exception e){
            throw new RuntimeException("No se pudo obtener la relación entre el voluntario y la emergencia");
        }
    }

    public Vol_Emergencia updateVol_Emergencia(Integer idVolEmergencia, Vol_Emergencia vol_Emergencia){
        String queryTxt = "UPDATE Vol_Emergencia SET idVoluntario = :idVoluntario, idEmergencia = :idEmergencia WHERE idVolEmergencia = :idVolEmergencia";
        try (Connection connection = sql2o.open()) {
            Query query = connection.createQuery(queryTxt)
                    .addParameter("idVolEmergencia", idVolEmergencia)
                    .addParameter("idVoluntario", vol_Emergencia.getIdVoluntario())
                    .addParameter("idEmergencia", vol_Emergencia.getIdEmergencia())
                    .addColumnMapping("ID_VOL_EMERGENCIA", "idVolEmergencia")
                    .addColumnMapping("ID_VOLUNTARIO", "idVoluntario")
                    .addColumnMapping("ID_EMERGENCIA", "idEmergencia");
            Vol_Emergencia vol_Emergencia1 = query.executeAndFetchFirst(Vol_Emergencia.class);
            return vol_Emergencia1;

        } catch(Exception e){
            throw new RuntimeException("No se pudo actualizar la relación entre el voluntario y la emergencia");
        }

    }

    public void deleteVol_Emergencia(Integer idVolEmergencia){
        String queryTxt = "DELETE FROM Vol_Emergencia WHERE idVolEmergencia = :idVolEmergencia";
        try (Connection connection = sql2o.open()) {
            Query query = connection.createQuery(queryTxt)
                    .addParameter("idVolEmergencia", idVolEmergencia);
            query.executeUpdate();

        } catch(Exception e){
            throw new RuntimeException("No se pudo eliminar la relación entre el voluntario y la emergencia");
        }
    }


}
