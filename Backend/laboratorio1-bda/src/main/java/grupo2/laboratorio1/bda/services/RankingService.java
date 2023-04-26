package grupo2.laboratorio1.bda.services;

import grupo2.laboratorio1.bda.models.Ranking;
import grupo2.laboratorio1.bda.repositories.IRankingRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RankingService {
    @Autowired
    IRankingRepository rankingRepository;

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
        Integer idRanking = ranking.getIdRanking();
        Integer puntaje = ranking.getPuntaje();
        if(idRanking != null && !existsRanking(idRanking)){
            throw new IllegalArgumentException("El ranking ingresado no existe");
        }

        if (puntaje != null && !(puntaje >= 0 && puntaje <= 10)){
            throw new IllegalArgumentException("El puntaje ingresado no es valido");
        }
    }

    public Ranking getRanking(@NonNull Integer idRanking){
        Ranking ranking = new Ranking();
        ranking.setIdRanking(idRanking);
        validarRanking(ranking);
        return rankingRepository.getRanking(idRanking);
    }

    public List<Ranking> getAllRankings(){
        return rankingRepository.getAllRankings();
    }

    public void updateRanking(Integer idRanking, Ranking ranking){
        ranking.setIdRanking(idRanking);
        validarRanking(ranking);
        rankingRepository.updateRanking(ranking);
    }

    public void deleteRanking(@NonNull Integer idRanking){
        Ranking ranking = new Ranking();
        ranking.setIdRanking(idRanking);
        validarRanking(ranking);
        rankingRepository.deleteRanking(idRanking);
    }

    public boolean existsRanking(Integer idRanking){
        return rankingRepository.existsRanking(idRanking);
    }
}
