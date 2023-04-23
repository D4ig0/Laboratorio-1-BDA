package grupo2.laboratorio1.bda.repositories;

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
                .addParameter("id_institucion", emergencia.getId_institucion())
                .executeUpdate().getKey();
            emergencia.setId_emergencia(id);
            return emergencia;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
