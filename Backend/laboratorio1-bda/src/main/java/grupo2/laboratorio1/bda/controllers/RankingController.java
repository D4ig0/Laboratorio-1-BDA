package grupo2.laboratorio1.bda.controllers;

import grupo2.laboratorio1.bda.models.Ranking;
import grupo2.laboratorio1.bda.services.RankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
public class RankingController {
    @Autowired
    RankingService rankingService;

    @PostMapping("/rankings")
    public void createRanking(@RequestParam Integer idVoluntario,
                              @RequestParam Integer idTarea,
                              @RequestParam Integer puntaje,
                              @RequestParam Boolean flgInvitado,
                              @RequestParam Boolean flgParticipa){
        try{
            rankingService.createRanking(idVoluntario, idTarea, puntaje, flgInvitado, flgParticipa);
        }
        catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("/rankings/{id}")
    public Ranking getRanking(@PathVariable("id") Integer idRanking){
        return rankingService.getRanking(idRanking);
    }

    @GetMapping("/rankings")
    public List<Ranking> getAllRankings(){
        return rankingService.getAllRankings();
    }

    @PutMapping("/rankings/{id}")
    public void updateRanking(@PathVariable("id") Integer idRanking, @RequestBody Ranking ranking){
        try {
            rankingService.updateRanking(idRanking, ranking);
        }
        catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @DeleteMapping("/rankings/{id}")
    public void deleteRanking(@PathVariable("id") Integer idRanking){
        try {
            rankingService.deleteRanking(idRanking);
        }
        catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
