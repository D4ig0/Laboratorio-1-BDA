package grupo2.laboratorio1.bda.controllers;

import grupo2.laboratorio1.bda.models.Ranking;
import grupo2.laboratorio1.bda.services.RankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
public class RankingController {
    @Autowired
    RankingService rankingService;

    @PostMapping("/rankings")
    public ResponseEntity createRanking(@RequestParam Integer idVoluntario,
                              @RequestParam Integer idTarea,
                              @RequestParam Integer puntaje,
                              @RequestParam Boolean flgInvitado,
                              @RequestParam Boolean flgParticipa){
        try{
            rankingService.createRanking(idVoluntario, idTarea, puntaje, flgInvitado, flgParticipa);
            return ResponseEntity.ok(null);
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/rankings/{id}")
    public ResponseEntity<Ranking> getRanking(@PathVariable("id") Integer idRanking){
        try {
            Ranking ranking = rankingService.getRanking(idRanking);
            return ResponseEntity.ok(ranking);
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.notFound().header("message", e.getMessage()).build();
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();
        }
    }

    @GetMapping("/rankings")
    public ResponseEntity<List<Ranking>> getAllRankings(){
        return ResponseEntity.ok(rankingService.getAllRankings());
    }

    @PutMapping("/rankings/{id}")
    public ResponseEntity updateRanking(@PathVariable("id") Integer idRanking, @RequestBody Ranking ranking){
        try {
            rankingService.updateRanking(idRanking, ranking);
            return ResponseEntity.ok(null);
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping("/rankings/{id}")
    public ResponseEntity deleteRanking(@PathVariable("id") Integer idRanking){
        try {
            rankingService.deleteRanking(idRanking);
            return ResponseEntity.ok().build();
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
