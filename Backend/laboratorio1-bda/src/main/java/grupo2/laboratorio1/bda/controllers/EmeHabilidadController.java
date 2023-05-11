package grupo2.laboratorio1.bda.controllers;

import grupo2.laboratorio1.bda.models.EmeHabilidad;
import grupo2.laboratorio1.bda.services.EmeHabilidadService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin
public class EmeHabilidadController {
    @Autowired
    private EmeHabilidadService emeHabilidadService;

    @PostMapping("/emeHabilidades")
    public ResponseEntity createEmeHabilidad(@RequestParam("idEmergencia") Integer idEmergencia,
                                    @RequestParam("idHabilidad") Integer idHabilidad){
        try{
            emeHabilidadService.createEmeHabilidad(idEmergencia, idHabilidad);
            return ResponseEntity.ok(null);
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }

    }

    @GetMapping("/emeHabilidades/{id}")
    public ResponseEntity<EmeHabilidad> getEmeHabilidad(@PathVariable("id") Integer idEmeHabilidad){
        try{
            EmeHabilidad emeHabilidad = emeHabilidadService.getEmeHabilidad(idEmeHabilidad);
            return ResponseEntity.ok(emeHabilidad);
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.notFound().header("message", e.getMessage()).build();
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();
        }
    }
    
    @GetMapping("/emeHabilidades")
    public ResponseEntity<List<EmeHabilidad>> getAllEmeHabilidad(){
        try{
            return ResponseEntity.ok(emeHabilidadService.getAllEmeHabilidad());
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();
        }
    }

    @PutMapping("/emeHabilidades/{id}")
    public ResponseEntity<EmeHabilidad> updateEmeHabilidad(@PathVariable("id") Integer idEmeHabilidad, @RequestBody EmeHabilidad emehabilidad){
        try{
           EmeHabilidad emeHabilidad = emeHabilidadService.updateEmeHabilidad(idEmeHabilidad,emehabilidad);
            return ResponseEntity.ok(emeHabilidad);
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.notFound().header("message", e.getMessage()).build();
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();
        }
    }

    @DeleteMapping("/emeHabilidades/{id}")
    public ResponseEntity deleteEmeHabilidad(@PathVariable("id") Integer idEmeHabilidad){
        try{
            emeHabilidadService.deleteEmeHabilidad(idEmeHabilidad);
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
