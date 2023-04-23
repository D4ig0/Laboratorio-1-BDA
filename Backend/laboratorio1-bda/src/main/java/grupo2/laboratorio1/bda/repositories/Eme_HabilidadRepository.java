package grupo2.laboratorio1.bda.repositories;

import grupo2.laboratorio1.bda.models.Eme_Habilidad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

@Repository
public class Eme_HabilidadRepository implements IEme_HabilidadRepository {
    @Autowired
    private Sql2o sql2o;

    @Override
    public void createEme_Habilidad(Eme_Habilidad eme_habilidad){
        String queryText = "INSERT INTO Eme_Habilidad (id_emergencia, id_habilidad) VALUES (:id_emergencia, :id_habilidad)";
        try( Connection Connection = sql2o.open()){
            Connection.createQuery(queryText)
                    .addParameter("id_emergencia", eme_habilidad.getIdEmergencia())
                    .addParameter("id_habilidad", eme_habilidad.getIdHabilidad())
                    .executeUpdate();
        }
        catch (Exception e){
            throw new RuntimeException("No se pudo crear la tabla relacion entre emergencia y habilidad");
        }
    }


}
