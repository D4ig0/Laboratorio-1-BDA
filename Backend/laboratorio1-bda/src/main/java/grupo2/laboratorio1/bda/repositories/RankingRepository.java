package grupo2.laboratorio1.bda.repositories;

import grupo2.laboratorio1.bda.models.Ranking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.util.List;


@Repository
public class RankingRepository implements IRankingRepository{
    @Autowired
    private Sql2o sql2o;

    @Override
    public void createRanking(Ranking ranking) {
        String queryText = "INSERT INTO ranking (id_voluntario, id_tarea, puntaje, flg_invitado, flg_participa) VALUES (:idVoluntario, :idTarea, :puntaje, :flgInvitado, :flgParticipa)";
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
    @Override
    public Ranking getRanking(Integer idRanking) {
        String queryText = "SELECT * FROM ranking WHERE id_ranking = :idRanking";

        try(Connection connection = sql2o.open()){
            Query query = connection.createQuery(queryText)
                    .addParameter("idRanking", idRanking)
                    .addColumnMapping("ID_RANKING", "idRanking")
                    .addColumnMapping("ID_VOLUNTARIO", "idVoluntario")
                    .addColumnMapping("ID_TAREA", "idTarea")
                    .addColumnMapping("FLG_INVITADO", "flgInvitado")
                    .addColumnMapping("FLG_PARTICIPA", "flgParticipa");
            Ranking ranking = query.executeAndFetchFirst(Ranking.class);
            return ranking;
        }
        catch (Exception e){
            throw new RuntimeException("Ocurrio un error al obtener el ranking");
        }
    }

    @Override
    public List<Ranking> getAllRankings() {
        String queryText = "SELECT * FROM ranking";

        try(Connection connection = sql2o.open()){
            Query query = connection.createQuery(queryText)
                    .addColumnMapping("ID_RANKING", "idRanking")
                    .addColumnMapping("ID_VOLUNTARIO", "idVoluntario")
                    .addColumnMapping("ID_TAREA", "idTarea")
                    .addColumnMapping("FLG_INVITADO", "flgInvitado")
                    .addColumnMapping("FLG_PARTICIPA", "flgParticipa");
            List<Ranking> rankings = query.executeAndFetch(Ranking.class);
            return rankings;
        }
        catch (Exception e){
            throw new RuntimeException("Ocurrio un error al obtener los rankings");
        }
    }

    @Override
    public void updateRanking(Ranking ranking) {
        String queryText = "UPDATE ranking SET " +
                "puntaje = COALESCE(:puntaje, puntaje), " +
                "flg_invitado = COALESCE(:flgInvitado, flg_invitado), " +
                "flg_participa = COALESCE(:flgParticipa, flg_participa) " +
                "WHERE id_ranking = :idRanking";

        try(Connection connection = sql2o.open()){
            Query query = connection.createQuery(queryText)
                    .addParameter("puntaje", ranking.getPuntaje())
                    .addParameter("flgInvitado", ranking.getFlgInvitado())
                    .addParameter("flgParticipa", ranking.getFlgParticipa())
                    .addParameter("idRanking", ranking.getIdRanking());
            query.executeUpdate();
        }
        catch (Exception e){
            throw new RuntimeException("Ocurrio un error al actualizar el ranking");
        }
    }

    @Override
    public void deleteRanking(Integer idRanking) {
        String queryText = "DELETE FROM ranking WHERE id_ranking = :idRanking";

        try(Connection connection = sql2o.open()){
            Query query = connection.createQuery(queryText)
                    .addParameter("idRanking", idRanking);
            query.executeUpdate();
        }
        catch (Exception e){
            throw new RuntimeException("Ocurrio un error al eliminar el ranking");
        }
    }

    @Override
    public boolean existsRanking(Integer idRanking){
        String queryText = "SELECT EXISTS(SELECT * FROM ranking WHERE id_ranking = :idRanking)";

        try(Connection connection = sql2o.open()){
            Query query = connection.createQuery(queryText)
                    .addParameter("idRanking", idRanking);
            boolean exists = query.executeAndFetchFirst(Boolean.class);
            return exists;
        }
        catch (Exception e){
            throw new RuntimeException("Ocurrio un error al realizar la query");
        }
    }
}
