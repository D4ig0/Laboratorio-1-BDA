package grupo2.laboratorio1.bda.controllers;

import grupo2.laboratorio1.bda.models.TareaHabilidad;
import grupo2.laboratorio1.bda.services.TareaHabilidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class TareaHabilidadController {

    @Autowired
    TareaHabilidadService tareaHabilidadService;

    @PostMapping("/tareaHabilidades")
    public ResponseEntity createTareaHabilidad(@RequestParam Integer idHabilidad, @RequestParam Integer idTarea){
        try{
            tareaHabilidadService.createTareaHabilidad(idHabilidad, idTarea);
            return ResponseEntity.ok(null);
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }


    @GetMapping("/tareaHabilidades/{id}")
    public ResponseEntity<TareaHabilidad> getTareaHabilidad(@PathVariable("id") Integer idTareaHabilidad){
        try{
            TareaHabilidad tareaHabilidad = tareaHabilidadService.getTareaHabilidad(idTareaHabilidad);
            return ResponseEntity.ok(tareaHabilidad);
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.notFound().header("message", e.getMessage()).build();
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();
        }
    }
    
    @GetMapping("/tareaHabilidades")
    public ResponseEntity<List<TareaHabilidad>> getAllTareaHabilidad(){
        try{
            return ResponseEntity.ok(tareaHabilidadService.getAllTareaHabilidad());
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();
        }
    }


    @PutMapping("/tareaHabilidades/{id}")
    public ResponseEntity<TareaHabilidad> updateTareaHabilidad(@PathVariable("id") Integer idTareaHabilidad, @RequestBody TareaHabilidad tareaHabilidad){
        try{
            TareaHabilidad tareaHabilidadRes = tareaHabilidadService.updateTareaHabilidad(idTareaHabilidad,tareaHabilidad);
            return ResponseEntity.ok(tareaHabilidadRes);
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.notFound().header("message", e.getMessage()).build();
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();
        }
    }


    @DeleteMapping("/tareaHabilidades/{id}")
    public ResponseEntity deleteTareaHabilidad(@PathVariable("id") Integer idTareaHabilidad){
        try{
            tareaHabilidadService.deleteTareaHabilidad(idTareaHabilidad);
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