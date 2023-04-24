package grupo2.laboratorio1.bda.repositories;

import grupo2.laboratorio1.bda.models.Voluntario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class VoluntarioRepository implements IVoluntarioRepository{
    @Autowired
    private Sql2o sql2o;

    @Override
    public void createVoluntario(Voluntario voluntario) {
        String queryText = "INSERT INTO voluntario (nombre, correo, password) values (:nombre, :correo, :password)";

        try(Connection connection = sql2o.open()){
            Query query = connection.createQuery(queryText)
                    .addParameter("nombre", voluntario.getNombre())
                    .addParameter("correo", voluntario.getCorreo())
                    .addParameter("password", voluntario.getPassword());
            query.executeUpdate();
        }
        catch (Exception e){
            throw new RuntimeException("Ocurrio un error al registrar el voluntario");
        }
    }

    @Override
    public Voluntario getVoluntario(Integer idVoluntario) {
        String queryText = "SELECT id_voluntario, nombre, correo FROM voluntario WHERE id_voluntario = :idVoluntario";

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
    public List<Voluntario> getAllVoluntarios() {
        String queryText = "SELECT id_voluntario, nombre, correo FROM voluntario";

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
                "password = COALESCE(:password, password) " +
                "WHERE id_voluntario = :idVoluntario";

        try(Connection connection = sql2o.open()){
            Query query = connection.createQuery(queryText)
                    .addParameter("nombre", voluntario.getNombre())
                    .addParameter("correo", voluntario.getCorreo())
                    .addParameter("password", voluntario.getPassword())
                    .addParameter("idVoluntario", voluntario.getIdVoluntario());
            query.executeUpdate();
        }
        catch (Exception e){
            throw new RuntimeException("Ocurrio un error al actualizar el voluntario");
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
    public String getEncodedPassword(String password) {
        String queryText = "SELECT CRYPT(:password, GEN_SALT('md5'))";

        try(Connection connection = sql2o.open()){
            Query query = connection.createQuery(queryText)
                    .addParameter("password", password);
            String encodedPassword = query.executeScalar(String.class);
            return encodedPassword;
        }
        catch (Exception e){
            throw new RuntimeException("Ocurrio un error al codificar la password");
        }
    }
}
