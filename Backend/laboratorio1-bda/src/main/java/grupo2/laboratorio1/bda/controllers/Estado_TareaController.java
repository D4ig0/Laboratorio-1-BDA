package grupo2.laboratorio1.bda.controllers;

import grupo2.laboratorio1.bda.models.Estado_Tarea;
import grupo2.laboratorio1.bda.services.Estado_TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
public class Estado_TareaController {
    @Autowired
    Estado_TareaService estado_tareaService;

    @PostMapping("/habilidades")
    public void createEstado_Tarea(@RequestParam Integer idEstado_Tarea,
                                @RequestParam String descripcion){
        try{
            estado_tareaService.createEstado_Tarea(idEstado_Tarea, descripcion);
        }
        catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("/habilidades/{id}")
    public Estado_Tarea getEstado_Tarea(@PathVariable("id") Integer idEstado_Tarea){
        try {
            return estado_tareaService.getEstado_Tarea(idEstado_Tarea);
        }
        catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("/habilidades")
    public List<Estado_Tarea> getAllEstado_Tareas(){
        return estado_tareaService.getAllEstado_Tareas();
    }

    @PutMapping("/voluntarios/{id}")
    public void updateEstado_Tarea(@PathVariable("id") Integer idEstado_Tarea, @RequestBody Estado_Tarea estado_tarea){
        try {
            estado_tareaService.updateEstado_Tarea(idEstado_Tarea, estado_tarea);
        }
        catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @DeleteMapping("/habilidades/{id}")
    public void deleteEstado_Tarea(@PathVariable("id") Integer idEstado_Tarea){
        try {
            estado_tareaService.deleteEstado_Tarea(idEstado_Tarea);
        }
        catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}