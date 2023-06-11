package grupo2.laboratorio1.bda.controllers;

import grupo2.laboratorio1.bda.models.Institucion;
import grupo2.laboratorio1.bda.services.InstitucionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/instituciones")
public class InstitucionController {
    @Autowired
    InstitucionService institucionService;

    @PostMapping
    public ResponseEntity createInstitucion(@RequestParam String nombre){
        try{
            Institucion institucion = institucionService.createInstitucion(nombre);
            return ResponseEntity.ok(institucion);
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
    @GetMapping
    public ResponseEntity<List<Institucion>> getAllInstituciones(){
        return ResponseEntity.ok(institucionService.getAllInstituciones());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Institucion> getInstitucion(@PathVariable("id") Integer id){
        return ResponseEntity.ok(institucionService.getInstitucion(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateInstitucion(@PathVariable("id") Integer id,  @RequestBody Institucion institucion){
        try {
            Institucion institucionActualizada = institucionService.updateInstitucion(id, institucion);
            return ResponseEntity.ok(institucionActualizada);
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteInstitucio(@PathVariable("id") Integer id){
        institucionService.deleteInstitucion(id);
        return ResponseEntity.ok().build();
    }
}
