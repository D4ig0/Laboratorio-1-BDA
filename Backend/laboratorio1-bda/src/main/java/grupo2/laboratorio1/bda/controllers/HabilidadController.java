package grupo2.laboratorio1.bda.controllers;

import grupo2.laboratorio1.bda.models.Habilidad;
import grupo2.laboratorio1.bda.services.HabilidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
public class HabilidadController {
    @Autowired
    HabilidadService habilidadService;

    @PostMapping("/habilidades")
    public ResponseEntity createHabilidad(@RequestParam String descripcion){
        try{
            habilidadService.createHabilidad(descripcion);
            return ResponseEntity.ok(null);
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/habilidades/{id}")
    public ResponseEntity<Habilidad> getHabilidad(@PathVariable("id") Integer idHabilidad){
        try {
            Habilidad habilidad =  habilidadService.getHabilidad(idHabilidad);
            return ResponseEntity.ok(habilidad);
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.notFound().header("message", e.getMessage()).build();
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();
        }
    }

    @GetMapping("/habilidades")
    public ResponseEntity<List<Habilidad>> getAllHabilidades(){
        return ResponseEntity.ok(habilidadService.getAllHabilidades());
    }

    @PutMapping("/habilidades/{id}")
    public ResponseEntity updateHabilidad(@PathVariable("id") Integer idHabilidad, @RequestBody Habilidad habilidad){
        try {
            habilidadService.updateHabilidad(idHabilidad, habilidad);
            return ResponseEntity.ok(null);
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.notFound().header("message", e.getMessage()).build();
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();
        }
    }

    @DeleteMapping("/habilidades/{id}")
    public ResponseEntity deleteHabilidad(@PathVariable("id") Integer idHabilidad){
        try {
            habilidadService.deleteHabilidad(idHabilidad);
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
