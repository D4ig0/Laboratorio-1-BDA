package grupo2.laboratorio1.bda.controllers;

import grupo2.laboratorio1.bda.models.EstadoTarea;
import grupo2.laboratorio1.bda.services.EstadoTareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
public class EstadoTareaController {
    @Autowired
    EstadoTareaService estadoTareaService;

    @PostMapping("/estadoTareas")
    public void createEstadoTarea(@RequestParam String descripcion){
        try{
            estadoTareaService.createEstadoTarea(descripcion);
        }
        catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("/estadoTareas/{id}")
    public EstadoTarea getEstadoTarea(@PathVariable("id") Integer idEstadoTarea){
        try {
            return estadoTareaService.getEstadoTarea(idEstadoTarea);
        }
        catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("/estadoTareas")
    public List<EstadoTarea> getAllEstadoTareas(){
        return estadoTareaService.getAllEstadoTareas();
    }

    @PutMapping("/estadoTareas/{id}")
    public void updateEstadoTarea(@PathVariable("id") Integer idEstadoTarea, @RequestBody EstadoTarea estadoTarea){
        try {
            estadoTareaService.updateEstadoTarea(idEstadoTarea, estadoTarea);
        }
        catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @DeleteMapping("/estadoTareas/{id}")
    public void deleteEstadoTarea(@PathVariable("id") Integer idEstadoTarea){
        try {
            estadoTareaService.deleteEstadoTarea(idEstadoTarea);
        }
        catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}