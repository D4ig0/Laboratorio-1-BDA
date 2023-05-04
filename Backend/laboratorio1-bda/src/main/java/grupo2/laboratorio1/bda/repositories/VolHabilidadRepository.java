package grupo2.laboratorio1.bda.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import grupo2.laboratorio1.bda.models.VolHabilidad;

@Repository
public class VolHabilidadRepository implements IVolHabilidadRepository {
    @Autowired
    private Sql2o sql2o;
    @Override
    public void createVolHabilidad(VolHabilidad volHabilidad) {
        String query = "INSERT INTO vol_habilidad (id_voluntario, id_habilidad) VALUES (:idVoluntario, :idHabilidad)";
        try (org.sql2o.Connection con = sql2o.open()) {
            con.createQuery(query)
                    .addParameter("idVoluntario", volHabilidad.getIdVoluntario())
                    .addParameter("idHabilidad", volHabilidad.getIdHabilidad())
                    .executeUpdate();
        } catch(Exception e){
            throw new RuntimeException("No se pudo crear la relación entre el voluntario y la emergencia");
        }
    }

    @Override
    public VolHabilidad getVolHabilidad(Integer idVolHabilidad){
        String queryTxt = "SELECT * FROM vol_habilidad WHERE id_vol_habilidad = :idVolHabilidad";
        try (Connection connection = sql2o.open()) {
            Query query = connection.createQuery(queryTxt)
                    .addParameter("idVolHabilidad", idVolHabilidad)
                    .addColumnMapping("ID_VOL_habilidad", "idVolHabilidad")
                    .addColumnMapping("ID_VOLUNTARIO", "idVoluntario")
                    .addColumnMapping("ID_HABILIDAD", "idHabilidad");
            VolHabilidad volHabilidad = query.executeAndFetchFirst(VolHabilidad.class);
            return volHabilidad;

        } catch(Exception e){
            throw new RuntimeException("No se pudo obtener la relación entre el voluntario y la emergencia");
        }
    }

    @Override
    public List<VolHabilidad> getAllVolHabilidad(){
        String queryTxt = "SELECT * FROM vol_habilidad";
        try (Connection connection = sql2o.open()) {
            Query query = connection.createQuery(queryTxt)
                    .addColumnMapping("ID_VOL_habilidad", "idVolHabilidad")
                    .addColumnMapping("ID_VOLUNTARIO", "idVoluntario")
                    .addColumnMapping("ID_HABILIDAD", "idHabilidad");
            List<VolHabilidad> volHabilidad = query.executeAndFetch(VolHabilidad.class);
            return volHabilidad;

        } catch(Exception e){
            throw new RuntimeException("No se pudo obtener la relación entre el voluntario y la emergencia");
        }
    }

    @Override
    public VolHabilidad updateVolHabilidad(Integer idVolHabilidad, VolHabilidad volHabilidad){
        String queryTxt = "UPDATE vol_habilidad SET id_voluntario = coalesce(:idVoluntario, id_voluntario), id_habilidad = coalesce(:idHabilidad, id_habilidad) WHERE id_vol_habilidad = :idVolHabilidad";
        try (Connection connection = sql2o.open()) {
            Query query = connection.createQuery(queryTxt)
                    .addParameter("idVolHabilidad", idVolHabilidad)
                    .addParameter("idVoluntario", volHabilidad.getIdVoluntario())
                    .addParameter("idHabilidad", volHabilidad.getIdHabilidad());
            query.executeUpdate();
            VolHabilidad volHabilidadActualizada = this.getVolHabilidad(idVolHabilidad);
            return volHabilidadActualizada;

        } catch(Exception e){
            throw new RuntimeException("No se pudo actualizar la relación entre el voluntario y la emergencia");
        }
    }

    @Override
    public void deleteVolHabilidad(Integer idVolHabilidad){
        String queryTxt = "DELETE FROM vol_habilidad WHERE id_vol_habilidad = :idVolHabilidad";
        try (Connection connection = sql2o.open()) {
            Query query = connection.createQuery(queryTxt)
                    .addParameter("idVolHabilidad", idVolHabilidad);
            query.executeUpdate();

        } catch(Exception e){
            throw new RuntimeException("No se pudo eliminar la relación entre el voluntario y la emergencia");
        }
    }

    @Override
    public List<VolHabilidad> getVolHabilidadByHabilidad(Integer idHabilidad) {
        String queryTxt = "SELECT * FROM vol_habilidad WHERE id_habilidad = :idHabilidad";
        try (Connection connection = sql2o.open()) {
            Query query = connection.createQuery(queryTxt)
                    .addParameter("idHabilidad", idHabilidad)
                    .addColumnMapping("ID_VOL_HABILIDAD", "idVolHabilidad")
                    .addColumnMapping("ID_VOLUNTARIO", "idVoluntario")
                    .addColumnMapping("ID_HABILIDAD", "idHabilidad");
            List<VolHabilidad> volHabilidades = query.executeAndFetch(VolHabilidad.class);
            return volHabilidades;

        } catch(Exception e){
            throw new RuntimeException("No se pudo obtener la relación entre el voluntario y la emergencia");
        }
    }

    @Override
    public List<VolHabilidad> getVolHabilidadByVoluntario(Integer idVoluntario) {
        String queryTxt = "SELECT * FROM vol_habilidad WHERE id_voluntario = :idVoluntario";
        try (Connection connection = sql2o.open()) {
            Query query = connection.createQuery(queryTxt)
                    .addParameter("idVoluntario", idVoluntario)
                    .addColumnMapping("ID_VOL_HABILIDAD", "idVolHabilidad")
                    .addColumnMapping("ID_VOLUNTARIO", "idVoluntario")
                    .addColumnMapping("ID_HABILIDAD", "idHabilidad");
            List<VolHabilidad> volHabilidades = query.executeAndFetch(VolHabilidad.class);
            return volHabilidades;

        } catch(Exception e){
            throw new RuntimeException("No se pudo obtener la relación entre el voluntario y la emergencia");
        }
    }

}
