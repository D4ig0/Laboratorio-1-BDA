package grupo2.laboratorio1.bda.controllers;

import grupo2.laboratorio1.bda.models.VolHabilidad;
import grupo2.laboratorio1.bda.services.VolHabilidadService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin
public class VolHabilidadController {
    @Autowired
    private VolHabilidadService volHabilidadService;

    @PostMapping("/volHabilidades")
    public ResponseEntity createVolHabilidad(@RequestParam("idVoluntario") Integer idVoluntario,
                                             @RequestParam("idHabilidad") Integer idHabilidad){
        try{
            volHabilidadService.createVolHabilidad(idVoluntario, idHabilidad);
            return ResponseEntity.ok().build();
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }

    }

    @GetMapping("/volHabilidades/{id}")
    public ResponseEntity<VolHabilidad> getVolHabilidad(@PathVariable("id") Integer id){
        try{
            VolHabilidad volHabilidad = volHabilidadService.getVolHabilidad(id);
            return ResponseEntity.ok(volHabilidad);
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().header("message", e.getMessage()).build();
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();
        }
    }

    @GetMapping("/volHabilidades")
    public ResponseEntity<List<VolHabilidad>> getAllVolHabilidad(){
        try{
            return ResponseEntity.ok(volHabilidadService.getAllVolHabilidad());
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();
        }
    }

    @PutMapping("/volHabilidades/{id}")
    public ResponseEntity<VolHabilidad> updateVolHabilidad(@PathVariable("id") Integer id, @RequestBody VolHabilidad volHabilidad){
        try{
            VolHabilidad volHabilidadRes = volHabilidadService.updateVolHabilidad(id, volHabilidad);
            return ResponseEntity.ok(volHabilidadRes);
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.notFound().header("message", e.getMessage()).build();
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();
        }
    }

    @DeleteMapping("/volHabilidades/{id}")
    public ResponseEntity deleteVolHabilidad(@PathVariable("id") Integer id){
        try{
            volHabilidadService.deleteVolHabilidad(id);
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
