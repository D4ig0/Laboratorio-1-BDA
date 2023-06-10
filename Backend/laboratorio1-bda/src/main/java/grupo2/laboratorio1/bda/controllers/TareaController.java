package grupo2.laboratorio1.bda.controllers;

import grupo2.laboratorio1.bda.services.TareaService;
import grupo2.laboratorio1.bda.models.Tarea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.sql.Date;
import java.util.List;


@RestController
@CrossOrigin
public class TareaController {
    @Autowired
    TareaService tareaService;

    @PostMapping("/tareas")
    public ResponseEntity createRanking(@RequestParam Integer idEmergencia,
                                                @RequestParam String nombre,
                                                @RequestParam String descripcion,
                                                @RequestParam Integer cantVolRequeridos,
                                                @RequestParam Integer cantVolInscritos,
                                                @RequestParam Date fechaInicio,
                                                @RequestParam Date fechaFin,
                                                @RequestParam String estadoActual){
        try{
            tareaService.createTarea(idEmergencia, nombre, descripcion,cantVolRequeridos, cantVolInscritos, fechaInicio, fechaFin, estadoActual);
            return ResponseEntity.ok(null);
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/tareas/{id}")
    public ResponseEntity<Tarea> getTarea(@PathVariable("id") Integer idTarea){
        Tarea tarea = tareaService.getTarea(idTarea);
        return ResponseEntity.ok(tarea);
    }

    @GetMapping("/tareas")
    public ResponseEntity<List<Tarea>> getAllTareas(){
        return ResponseEntity.ok(tareaService.getAllTareas());
    }

    @PutMapping("/tareas/{id}")
    public ResponseEntity updateTarea(@PathVariable("id") Integer idTarea, @RequestBody Tarea tarea){
        try {
            tareaService.updateTarea(idTarea, tarea);
            return ResponseEntity.ok(null);
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
    @DeleteMapping("/tareas/{id}")
    public ResponseEntity deleteTarea(@PathVariable("id") Integer idTarea){
        tareaService.deleteTarea(idTarea);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/tareas/gtbe/{idE}")
    public ResponseEntity<Integer> getTotalTareasByEmergencia(@PathVariable("idE") Integer idEmergencia){
        try {
             Integer totalTareas = tareaService.getTotalTareasByEmergencia(idEmergencia);
            return ResponseEntity.ok(totalTareas);
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.notFound().header("message", e.getMessage()).build();
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();
        }
    }

    @GetMapping("/tareas/gtr/{idR}")
    public ResponseEntity<List<Tarea>> getTareasEnRegion(@PathVariable("idR") Integer idRegion){
         try {
             List<Tarea> Tareas =  tareaService.getTareasEnRegion(idRegion);;
             return ResponseEntity.ok(Tareas);
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.notFound().header("message", e.getMessage()).build();
         }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();
         }
    }
}
