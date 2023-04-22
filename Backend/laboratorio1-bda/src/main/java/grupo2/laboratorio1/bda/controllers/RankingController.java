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

    @PostMapping("/ranking")
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
}
