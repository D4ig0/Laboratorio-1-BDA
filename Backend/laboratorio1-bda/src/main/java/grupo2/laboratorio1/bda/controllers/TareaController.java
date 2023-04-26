package grupo2.laboratorio1.bda.controllers;

import grupo2.laboratorio1.bda.services.TareaService;
import grupo2.laboratorio1.bda.models.Tarea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public void createRanking(@RequestParam Integer idEmergencia,
                              @RequestParam String nombre,
                              @RequestParam String descripcion,
                              @RequestParam Integer cantVolRequeridos,
                              @RequestParam Integer cantVolInscritos, 
                              @RequestParam Date fechaInicio,
                              @RequestParam Date fechaFin,
                              @RequestParam String estadoActual){
        try{
            tareaService.createTarea(idEmergencia, nombre, descripcion,cantVolRequeridos, cantVolInscritos, fechaInicio, fechaFin, estadoActual);
        }
        catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("/tareas/{id}")
    public Tarea getTarea(@PathVariable("id") Integer idTarea){
        return tareaService.getTarea(idTarea);
    }

    @GetMapping("/tareas")
    public List<Tarea> getAllTareas(){
        return tareaService.getAllTareas();
    }

}