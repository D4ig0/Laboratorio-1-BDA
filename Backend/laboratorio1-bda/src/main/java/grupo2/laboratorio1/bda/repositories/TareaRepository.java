package grupo2.laboratorio1.bda.repositories;

import grupo2.laboratorio1.bda.models.Tarea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;



@Repository
public class TareaRepository implements ITareaRepository{
    @Autowired
    private Sql2o sql2o;

    @Override
    public void createTarea(Tarea tarea) {
        String queryText = "INSERT INTO tarea ( id_emergencia, nombre , descripcion, cant_vol_requeridos , cant_vol_inscritos ,fecha_inicio ,fecha_fin ,estado_actual) " +
                "VALUES (:idEmergencia, :nombre, :descripcion , :cantVolRequeridos , :cantVolInscritos, :fechaInicio , :fechaFin , :estadoActual)";

        try(Connection connection = sql2o.open()){
            Query query = connection.createQuery(queryText)
                                    .addParameter("idEmergencia", tarea.getIdEmergencia())
                                    .addParameter("nombre", tarea.getNombre())
                                    .addParameter("descripcion ", tarea.getDescripcion())
                                    .addParameter("cantVolRequeridos ", tarea.getCantVolRequeridos())
                                    .addParameter("cantVolInscritos", tarea.getCantVolInscritos())
                                    .addParameter("fechaInicio", tarea.getFechaInicio())
                                    .addParameter("fechaFin", tarea.getFechaFin())
                                    .addParameter("estadoActual", tarea.getEstadoActual());
            query.executeUpdate();
        }
        catch (Exception e){
            throw new RuntimeException("Ocurrio un error al generar la tarea");
        }
    }
}