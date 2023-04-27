package grupo2.laboratorio1.bda.repositories;

import grupo2.laboratorio1.bda.models.Habilidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class HabilidadRepository implements IHabilidadRepository{
    @Autowired
    private Sql2o sql2o;

    @Override
    public void createHabilidad(Habilidad habilidad) {
        String queryText = "INSERT INTO ranking (id_habilidad, descripcion) " +
                "VALUES (:idHabilidad, :descripcion)";

        try(Connection connection = sql2o.open()){
            Query query = connection.createQuery(queryText)
                    .addParameter("idHabilidad", habilidad.getIdHabilidad())
                    .addParameter("descripcion", habilidad.getDescripcion());
            query.executeUpdate();
        }
        catch (Exception e){
            throw new RuntimeException("Ocurrio un error al registrar el ranking");
        }
    }
}
