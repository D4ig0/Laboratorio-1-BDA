package grupo2.laboratorio1.bda.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.sql2o.Connection;
import org.springframework.stereotype.Repository;
import org.sql2o.Sql2o;

import grupo2.laboratorio1.bda.models.Emergencia;

@Repository
public class EmergenciaRepository implements IEmergenciaRepository{
    @Autowired
    private Sql2o sql2o;

    @Override
    public Emergencia createEmergencia(Emergencia emergencia){
        String query = "INSERT INTO emergencia (nombre, descripcion, fecha_inicio, fecha_termino, activo, id_institucion) values (:nombre, :descripcion, :fecha_inicio, :fecha_termino, :activo, :id_institucion)";
        try(Connection conn = sql2o.open()){
            Integer id = (Integer) conn.createQuery(query)
                .addParameter("nombre", emergencia.getNombre())
                .addParameter("descripcion", emergencia.getDescripcion())
                .addParameter("fecha_inicio", emergencia.getFecha_inicio())
                .addParameter("fecha_termino", emergencia.getFecha_termino())
                .addParameter("activo", emergencia.getActivo())
                .addParameter("id_institucion", emergencia.getIdInstitucion())
                .executeUpdate().getKey();
            emergencia.setIdEmergencia(id);
            return emergencia;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Emergencia getEmergencia(Integer id_emergencia){
        String query = "SELECT * FROM emergencia WHERE id_emergencia = :id_emergencia";
        try(Connection conn = sql2o.open()){
            Emergencia emergencia = conn.createQuery(query)
                .addParameter("id_emergencia", id_emergencia)
                .executeAndFetchFirst(Emergencia.class);
            return emergencia;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Emergencia> getAllEmergencias(){
        String query = "SELECT * FROM emergencia";
        try(Connection conn = sql2o.open()){
            List<Emergencia> emergencias = conn.createQuery(query)
                .executeAndFetch(Emergencia.class);
            return emergencias;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Emergencia updateEmergencia(Emergencia emergencia){
        String query = "UPDATE emergencia SET "+ 
                        "nombre = COALESCE(:nombre, nombre), "+
                        "descripcion = COALESCE(:descripcion, descripcion), "+
                        "fecha_inicio = COALESCE(:fecha_inicio, fecha_inicio), "+
                        "fecha_termino = COALESCE(:fecha_termino, fecha_termino), "+
                        "activo = COALESCE(:activo, activo), "+
                        "id_institucion = COALESCE(:id_institucion, id_institucion) "+
                        "WHERE id_emergencia = :id_emergencia";
        try(Connection conn = sql2o.open()){
            conn.createQuery(query)
                .addParameter("nombre", emergencia.getNombre())
                .addParameter("descripcion", emergencia.getDescripcion())
                .addParameter("fecha_inicio", emergencia.getFecha_inicio())
                .addParameter("fecha_termino", emergencia.getFecha_termino())
                .addParameter("activo", emergencia.getActivo())
                .addParameter("id_institucion", emergencia.getIdInstitucion())
                .addParameter("id_emergencia", emergencia.getIdEmergencia())
                .executeUpdate();
            return emergencia;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Boolean deleteEmergencia(Integer id_emergencia){
        String query = "DELETE FROM emergencia WHERE id_emergencia = :id_emergencia";
        try(Connection conn = sql2o.open()){
            conn.createQuery(query)
                .addParameter("id_emergencia", id_emergencia)
                .executeUpdate();
            return true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}
