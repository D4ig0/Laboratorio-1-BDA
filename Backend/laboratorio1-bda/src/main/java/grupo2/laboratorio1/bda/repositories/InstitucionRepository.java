package grupo2.laboratorio1.bda.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import grupo2.laboratorio1.bda.models.Institucion;

@Repository
public class InstitucionRepository implements IInstitucionRepository{
    @Autowired
    private Sql2o sql2o;

    // Creacion CRUD
    // Create
    @Override
    public Institucion createInstitucion(Institucion institucion) {
        String queryText = "INSERT INTO institucion (nombre) values (:nombre)";
        try(Connection conn = sql2o.open()){
            Query query = conn.createQuery(queryText)
            .addParameter("nombre", institucion.getNombre());
            query.executeUpdate();
        }
        catch (Exception e) {
            throw new RuntimeException("Ocurrio un error al registrar la institucion");
        }
        return institucion;
    }

    // Read
    @Override
    public Institucion getInstitucion(Integer id_institucion) {
        try(Connection conn = sql2o.open()){
            return conn.createQuery("select * from institucion where id_institucion = :id_institucion")
                    .addParameter("id_institucion", id_institucion)
                    .addColumnMapping("ID_INSTITUCION", "idInstitucion")
                    .addColumnMapping("NOMBRE", "nombre")
                    .executeAndFetchFirst(Institucion.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override 
    public List<Institucion> getAllInstituciones() {
        try(Connection conn = sql2o.open()){
            return conn.createQuery("select * from institucion")
                    .addColumnMapping("ID_INSTITUCION", "idInstitucion")
                    .addColumnMapping("NOMBRE", "nombre")
                    .executeAndFetch(Institucion.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    // Update
    @Override
    public Institucion updateInstitucion(Institucion institucion) {
        try(Connection conn = sql2o.open()){
            conn.createQuery("UPDATE institucion SET nombre = COALESCE(:nombre,nombre) WHERE id_institucion = :id_institucion")
                    .addParameter("nombre", institucion.getNombre())
                    .addParameter("id_institucion", institucion.getIdInstitucion())
                    .executeUpdate();
            return institucion;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    // Delete
    @Override
    public boolean deleteInstitucion(Integer id_institucion) {
        try(Connection conn = sql2o.open()){
            conn.createQuery("DELETE FROM institucion WHERE id_institucion = :id_institucion")
                    .addParameter("id_institucion", id_institucion)
                    .executeUpdate();
            return true;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}
