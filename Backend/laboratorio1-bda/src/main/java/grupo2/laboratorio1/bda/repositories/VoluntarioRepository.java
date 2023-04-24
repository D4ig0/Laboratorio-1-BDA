package grupo2.laboratorio1.bda.repositories;

import grupo2.laboratorio1.bda.models.Voluntario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

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
