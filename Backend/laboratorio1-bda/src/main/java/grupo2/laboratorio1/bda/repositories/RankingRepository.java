package grupo2.laboratorio1.bda.repositories;

import grupo2.laboratorio1.bda.models.Ranking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RankingRepository implements IRankingRepository{
    @Autowired
    private Sql2o sql2o;

    @Override
    public void createRanking(Ranking ranking) {
        String queryText = "INSERT INTO ranking (id_voluntario, id_tarea, puntaje, flg_invitado, flg_participa) " +
                "VALUES (:idVoluntario, :idTarea, :puntaje, :flgInvitado, :flgParticipa)";

        try(Connection connection = sql2o.open()){
            Query query = connection.createQuery(queryText)
                                    .addParameter("idVoluntario", ranking.getIdVoluntario())
                                    .addParameter("idTarea", ranking.getIdTarea())
                                    .addParameter("puntaje", ranking.getPuntaje())
                                    .addParameter("flgInvitado", ranking.getFlgInvitado())
                                    .addParameter("flgParticipa", ranking.getFlgParticipa());
            query.executeUpdate();
        }
        catch (Exception e){
            throw new RuntimeException("Ocurrio un error al registrar el ranking");
        }
    }
}
