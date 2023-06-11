package grupo2.laboratorio1.bda.controllers;

import grupo2.laboratorio1.bda.services.VoluntarioService;
import grupo2.laboratorio1.bda.models.Voluntario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin
public class VoluntarioController {
    @Autowired
    VoluntarioService voluntarioService;

    @PostMapping("/voluntarios")
    public ResponseEntity createVoluntario(@RequestParam String nombre, @RequestParam String correo, @RequestParam String password, @RequestParam Double longitud, @RequestParam Double latitud){
        try{
            voluntarioService.createVoluntario(nombre, correo, password, longitud, latitud);
            return ResponseEntity.ok(null);
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/voluntarios/{id}")
    public ResponseEntity<Voluntario> getVoluntario(@PathVariable("id") Integer idVoluntario){
        try {
            Voluntario voluntario = voluntarioService.getVoluntario(idVoluntario);
            return ResponseEntity.ok(voluntario);
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.notFound().header("message", e.getMessage()).build();
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();
        }
    }

    @GetMapping("/voluntarios")
    public ResponseEntity<List<Voluntario>> getAllVoluntarios(){
        return ResponseEntity.ok(voluntarioService.getAllVoluntarios());
    }

    @PutMapping("/voluntarios/{id}")
    public ResponseEntity updateVoluntario(@PathVariable("id") Integer idVoluntario, @RequestBody Voluntario voluntario){
        try {
            voluntarioService.updateVoluntario(idVoluntario, voluntario);
            return ResponseEntity.ok(null);
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping("/voluntarios/{id}")
    public ResponseEntity deleteVoluntario(@PathVariable("id") Integer idVoluntario){
        try {
            voluntarioService.deleteVoluntario(idVoluntario);
            return ResponseEntity.ok().build();
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/voluntarios/emergencia/{id}/{radio}")
    public ResponseEntity<List<Voluntario>> voluntariosEnRadioEmergencia(@PathVariable("id") Integer idEmergencia, @PathVariable("radio") Double radio){
        try {
            List<Voluntario> voluntarios = voluntarioService.findVoluntarioForEmergencia(radio, idEmergencia);
            return ResponseEntity.ok(voluntarios);
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.notFound().header("message", e.getMessage()).build();
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();
        }
    }
}
