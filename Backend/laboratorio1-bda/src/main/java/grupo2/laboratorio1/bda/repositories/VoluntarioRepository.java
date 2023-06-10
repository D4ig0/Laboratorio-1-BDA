package grupo2.laboratorio1.bda.repositories;

import grupo2.laboratorio1.bda.models.Voluntario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;
import java.util.List;
import java.util.Optional;

@Repository
public class VoluntarioRepository implements IVoluntarioRepository{
    @Autowired
    private Sql2o sql2o;

    @Override
    public void createVoluntario(Voluntario voluntario,Double longitud, Double latitud) {
        String queryText = "INSERT INTO voluntario (nombre, correo, password,ubicacion) values (:nombre, :correo, :password, ST_SetSRID(ST_Point(:longitud, :latitud), 4326))";
        try(Connection connection = sql2o.open()){
            Query query = connection.createQuery(queryText)
                    .addParameter("nombre", voluntario.getNombre())
                    .addParameter("correo", voluntario.getCorreo())
                    .addParameter("password", voluntario.getPassword())
                    .addParameter("longitud", longitud)
                    .addParameter("latitud", latitud);
            query.executeUpdate();
        }
        catch (Exception e){
            throw new RuntimeException("Ocurrio un error al registrar el voluntario");
        }
    }

    @Override
    public Voluntario getVoluntario(Integer idVoluntario) {
        String queryText = "SELECT id_voluntario, nombre, correo, ST_X(ubicacion) as longitud, ST_Y(ubicacion) as latitud FROM voluntario WHERE id_voluntario = :idVoluntario";
        try(Connection connection = sql2o.open()){
            Query query = connection.createQuery(queryText)
                    .addParameter("idVoluntario", idVoluntario)
                    .addColumnMapping("ID_VOLUNTARIO", "idVoluntario");
            Voluntario voluntario = query.executeAndFetchFirst(Voluntario.class);
            return voluntario;
        }
        catch (Exception e){
            throw new RuntimeException("Ocurrio un error al obtener el voluntario");
        }
    }

    @Override
    public Optional<Voluntario> getVoluntarioByCorreo(String correo) {
        String queryText = "SELECT id_voluntario, nombre, correo, password FROM voluntario WHERE correo = :correo";

        try(Connection connection = sql2o.open()){
            Query query = connection.createQuery(queryText)
                    .addParameter("correo", correo)
                    .addColumnMapping("ID_VOLUNTARIO", "idVoluntario");
            Voluntario voluntario = query.executeAndFetchFirst(Voluntario.class);
            return Optional.ofNullable(voluntario);
        }
        catch (Exception e){
            throw new RuntimeException("Ocurrio un error al obtener el voluntario");
        }
    }

    @Override
    public List<Voluntario> getAllVoluntarios() {
        String queryText = "SELECT id_voluntario, nombre, correo, ST_X(ubicacion) as longitud, ST_Y(ubicacion) as latitud FROM voluntario";

        try(Connection connection = sql2o.open()){
            Query query = connection.createQuery(queryText)
                    .addColumnMapping("ID_VOLUNTARIO", "idVoluntario");
            List<Voluntario> voluntarios = query.executeAndFetch(Voluntario.class);
            return voluntarios;
        }
        catch (Exception e){
            throw new RuntimeException("Ocurrio un error al obtener los voluntarios");
        }
    }

    @Override
    public void updateVoluntario(Voluntario voluntario) {
        String queryText = "UPDATE voluntario SET " +
                "nombre = COALESCE(:nombre, nombre), " +
                "correo = COALESCE(:correo, correo), " +
                "password = COALESCE(:password, password), " +
                "ubicacion = COALESCE(ST_SetSRID(ST_Point(:longitud, :latitud), 4326), ubicacion)"+
                "WHERE id_voluntario = :idVoluntario";

        try(Connection connection = sql2o.open()){
            Query query = connection.createQuery(queryText)
                    .addParameter("nombre", voluntario.getNombre())
                    .addParameter("correo", voluntario.getCorreo())
                    .addParameter("password", voluntario.getPassword())
                    .addParameter("longitud", voluntario.getLongitud())
                    .addParameter("latitud", voluntario.getLatitud())
                    .addParameter("idVoluntario", voluntario.getIdVoluntario());
            query.executeUpdate();
        }
        catch (Exception e){
            throw new RuntimeException("Ocurrio un error al actualizar el voluntario");
        }
    }

    @Override
    public void deleteVoluntario(Integer idVoluntario) {
        String queryText = "DELETE FROM voluntario WHERE id_voluntario = :idVoluntario";

        try(Connection connection = sql2o.open()){
            Query query = connection.createQuery(queryText)
                    .addParameter("idVoluntario", idVoluntario);
            query.executeUpdate();
        }
        catch (Exception e){
            throw new RuntimeException("Ocurrio un error al eliminar el voluntario");
        }
    }

    @Override
    public boolean existsVoluntario(Integer idVoluntario){
        String queryText = "SELECT EXISTS(SELECT id_voluntario FROM voluntario WHERE id_voluntario = :idVoluntario)";

        try(Connection connection = sql2o.open()){
            Query query = connection.createQuery(queryText)
                    .addParameter("idVoluntario", idVoluntario);
            boolean exists = query.executeAndFetchFirst(Boolean.class);
            return exists;
        }
        catch (Exception e){
            throw new RuntimeException("Ocurrio un error al realizar la query");
        }
    }

    @Override
    public boolean existsVoluntarioByCorreo(String correo){
        String queryText = "SELECT EXISTS(SELECT id_voluntario FROM voluntario WHERE correo = :correo)";

        try(Connection connection = sql2o.open()){
            Query query = connection.createQuery(queryText)
                    .addParameter("correo", correo);
            boolean exists = query.executeAndFetchFirst(Boolean.class);
            return exists;
        }
        catch (Exception e){
            throw new RuntimeException("Ocurrio un error al realizar la query");
        }
    }

    @Override
    public List<Voluntario> findVoluntarioForEmergencia(Double radio, Integer idEmergencia){
        String queryText = "SELECT DISTINCT v.id_voluntario, v.nombre, v.correo, ST_X(v.ubicacion) as longitud, ST_Y(v.ubicacion) as latitud " +
                "FROM voluntario v , emergencia e, vol_habilidad vh, eme_habilidad eh " +
                "WHERE e.id_emergencia = :idEmergencia "+
                "AND vh.id_voluntario = v.id_voluntario " +
                "AND eh.id_emergencia = e.id_emergencia " +
                "AND vh.id_habilidad = eh.id_habilidad " +
                "AND ST_DISTANCE(v.ubicacion, e.ubicacion, true) <= :radio";

        try(Connection connection = sql2o.open()){
            Query query = connection.createQuery(queryText)
                    .addParameter("radio", radio)
                    .addParameter("idEmergencia", idEmergencia)
                    .addColumnMapping("ID_VOLUNTARIO", "idVoluntario");
            List<Voluntario> voluntarios = query.executeAndFetch(Voluntario.class);
            return voluntarios;
        }
        catch (Exception e){
            throw new RuntimeException("Ocurrio un error al obtener los voluntarios");
        }
    }

}
