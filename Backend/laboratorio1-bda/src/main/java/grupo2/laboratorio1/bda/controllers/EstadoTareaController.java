package grupo2.laboratorio1.bda.controllers;

import grupo2.laboratorio1.bda.models.EstadoTarea;
import grupo2.laboratorio1.bda.services.EstadoTareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
public class EstadoTareaController {
    @Autowired
    EstadoTareaService estadoTareaService;

    @PostMapping("/estadoTareas")
    public ResponseEntity createEstadoTarea(@RequestParam String descripcion){
        try{
            estadoTareaService.createEstadoTarea(descripcion);
            return ResponseEntity.ok(null);
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/estadoTareas/{id}")
    public ResponseEntity<EstadoTarea> getEstadoTarea(@PathVariable("id") Integer idEstadoTarea){
        try {
            EstadoTarea estadoTarea = estadoTareaService.getEstadoTarea(idEstadoTarea);
            return ResponseEntity.ok(estadoTarea);
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.notFound().header("message", e.getMessage()).build();
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();
        }
    }

    @GetMapping("/estadoTareas")
    public ResponseEntity<List<EstadoTarea>> getAllEstadoTareas(){
        return ResponseEntity.ok(estadoTareaService.getAllEstadoTareas());
    }

    @PutMapping("/estadoTareas/{id}")
    public ResponseEntity updateEstadoTarea(@PathVariable("id") Integer idEstadoTarea, @RequestBody EstadoTarea estadoTarea){
        try {
            estadoTareaService.updateEstadoTarea(idEstadoTarea, estadoTarea);
            return ResponseEntity.ok(null);
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping("/estadoTareas/{id}")
    public ResponseEntity deleteEstadoTarea(@PathVariable("id") Integer idEstadoTarea){
        try {
            estadoTareaService.deleteEstadoTarea(idEstadoTarea);
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