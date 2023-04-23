package grupo2.laboratorio1.bda.repositories;

import grupo2.laboratorio1.bda.models.Ranking;

import java.util.List;

public interface IRankingRepository {
    void createRanking(Ranking ranking);
    Ranking getRanking(Integer idRanking);
    List<Ranking> getAllRankings();
    void updateRanking(Ranking ranking);
    void deleteRanking(Integer idRanking);
    boolean existsRanking(Integer idRanking);
}
