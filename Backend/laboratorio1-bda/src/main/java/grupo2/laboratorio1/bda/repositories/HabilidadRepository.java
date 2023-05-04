package grupo2.laboratorio1.bda.repositories;

import grupo2.laboratorio1.bda.models.Habilidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;
import java.util.List;

@Repository
public class HabilidadRepository implements IHabilidadRepository{
    @Autowired
    private Sql2o sql2o;

    @Override
    public void createHabilidad(Habilidad habilidad) {
        String queryText = "INSERT INTO habilidad (descripcion) VALUES (:descripcion)";

        try(Connection connection = sql2o.open()){
            Query query = connection.createQuery(queryText)
                    .addParameter("descripcion", habilidad.getDescripcion());
            query.executeUpdate();
        }
        catch (Exception e){
            throw new RuntimeException("Ocurrio un error al registrar el ranking");
        }
    }

    @Override
    public Habilidad getHabilidad(Integer idHabilidad) {
        String queryText = "SELECT id_habilidad, descripcion FROM habilidad WHERE id_habilidad = :idHabilidad";

        try(Connection connection = sql2o.open()){
            Query query = connection.createQuery(queryText)
                    .addParameter("idHabilidad", idHabilidad)
                    .addColumnMapping("ID_HABILIDAD", "idHabilidad");
            Habilidad habilidad = query.executeAndFetchFirst(Habilidad.class);
            return habilidad;
        }
        catch (Exception e){
            throw new RuntimeException("Ocurrio un error al obtener la habilidad");
        }
    }

    @Override
    public List<Habilidad> getAllHabilidades() {
        String queryText = "SELECT id_habilidad, descripcion FROM habilidad";

        try(Connection connection = sql2o.open()){
            Query query = connection.createQuery(queryText)
                    .addColumnMapping("ID_HABILIDAD", "idHabilidad");
            List<Habilidad> habilidades = query.executeAndFetch(Habilidad.class);
            return habilidades;
        }
        catch (Exception e){
            throw new RuntimeException("Ocurrio un error al obtener las habilidades");
        }
    }

    @Override
    public void updateHabilidad(Habilidad habilidad) {
        String queryText = "UPDATE habilidad SET descripcion = coalesce(:descripcion, descripcion) WHERE id_habilidad = :idHabilidad";
        try(Connection connection = sql2o.open()){
            Query query = connection.createQuery(queryText)
                    .addParameter("descripcion", habilidad.getDescripcion())
                    .addParameter("idHabilidad", habilidad.getIdHabilidad());
            query.executeUpdate();
        }
        catch (Exception e){
            throw new RuntimeException("Ocurrio un error al actualizar la habilidad");
        }
    }

    @Override
    public void deleteHabilidad(Integer idHabilidad) {
        String queryText = "DELETE FROM habilidad WHERE id_habilidad = :idHabilidad";

        try(Connection connection = sql2o.open()){
            Query query = connection.createQuery(queryText)
                    .addParameter("idHabilidad", idHabilidad);
            query.executeUpdate();
        }
        catch (Exception e){
            throw new RuntimeException("Ocurrio un error al eliminar la habilidad");
        }
    }
}
