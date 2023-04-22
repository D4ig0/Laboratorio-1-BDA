package grupo2.laboratorio1.bda.services;

import grupo2.laboratorio1.bda.models.Ranking;
import grupo2.laboratorio1.bda.repositories.RankingRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RankingService {
    @Autowired
    RankingRepository rankingRepository;

    public void createRanking(@NonNull Integer idVoluntario,
                              @NonNull Integer idTarea,
                              Integer puntaje,
                              Boolean flgInvitado,
                              Boolean flgParticipa){
        Ranking ranking = new Ranking(null, idVoluntario, idTarea, puntaje, flgInvitado, flgParticipa);
        validarRanking(ranking);
        rankingRepository.createRanking(ranking);
    }

    private void validarRanking(Ranking ranking){
        Integer puntaje = ranking.getPuntaje();
        if (puntaje != null && !(puntaje >= 0 && puntaje <= 10)){
            throw new IllegalArgumentException("El puntaje ingresado no es valido");
        }
    }

    public Ranking getRanking(@NonNull Integer idRanking){
        return rankingRepository.getRanking(idRanking);
    }

    public List<Ranking> getAllRankings(){
        return rankingRepository.getAllRankings();
    }
}
