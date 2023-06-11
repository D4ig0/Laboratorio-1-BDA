package grupo2.laboratorio1.bda.repositories;

import grupo2.laboratorio1.bda.models.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class RegionRepository implements IRegionRepository {
    @Autowired
    private Sql2o sql2o;

    @Override
    public List<Region> getAllRegiones(){
        String queryText = "SELECT gid, nom_reg FROM division_regional WHERE nom_reg is not null";
        try(Connection connection = sql2o.open()){
            Query query = connection.createQuery(queryText)
                    .addColumnMapping("gid", "id")
                    .addColumnMapping("nom_reg", "nombre");
            List<Region> regiones = query.executeAndFetch(Region.class);
            return regiones;
        }
        catch (Exception e){
            throw new RuntimeException("Ocurrio un error al obtener las regiones");
        }
    }
}
