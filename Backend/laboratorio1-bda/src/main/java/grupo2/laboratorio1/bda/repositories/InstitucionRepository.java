package grupo2.laboratorio1.bda.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
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
        try(Connection conn = sql2o.open()){
            Integer insertedId = (Integer) conn.createQuery("INSERT INTO institucion (nombre) values (:institucionNombre)", true)
                    .addParameter("institucionNombre", institucion.getNombre())
                    .executeUpdate().getKey();
            institucion.setId_institucion(insertedId);
            return institucion;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    // Read
    @Override
    public Institucion getInstitucion(Integer id_institucion) {
        try(Connection conn = sql2o.open()){
            return conn.createQuery("select * from institucion where id_institucion = :id_institucion")
                    .addParameter("id_institucion", id_institucion)
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
            conn.createQuery("UPDATE institucion SET nombre = :nombre WHERE id_institucion = :id_institucion")
                    .addParameter("nombre", institucion.getNombre())
                    .addParameter("id_institucion", institucion.getId_institucion())
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
