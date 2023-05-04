package grupo2.laboratorio1.bda.controllers;

import grupo2.laboratorio1.bda.models.TareaHabilidad;
import grupo2.laboratorio1.bda.services.TareaHabilidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
public class TareaHabilidadController {

    @Autowired
    TareaHabilidadService tarea_HabilidadService;

    @PostMapping("/tareaHabilidades")
    public void createTareaHabilidad(@RequestParam Integer idEmergencia, @RequestParam Integer idHabilidad, @RequestParam Integer idTarea){
        try{
            tarea_HabilidadService.createTareaHabilidad(idEmergencia,  idHabilidad, idTarea);
        }
        catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }


    @GetMapping("/tareaHabilidades/{id}")
    public TareaHabilidad getTareaHabilidad(@PathVariable("id") Integer idTareaHabilidad){
        try{
            return tarea_HabilidadService.getTareaHabilidad(idTareaHabilidad);
        }
        catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
    
    @GetMapping("/tareaHabilidades")
    public List<TareaHabilidad> getAllTareaHabilidad(){
        try{
            return tarea_HabilidadService.getAllTareaHabilidad();
        }
        catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }


    @PutMapping("/tareaHabilidades/{id}")
    public TareaHabilidad updateTareaHabilidad(@PathVariable("id") Integer idTareaHabilidad, @RequestBody TareaHabilidad tareaHabilidad){
        try{
            return tarea_HabilidadService.updateTareaHabilidad(idTareaHabilidad,tareaHabilidad);
        }
        catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }


    @DeleteMapping("/tareaHabilidades/{id}")
    public void deleteTareaHabilidad(@PathVariable("id") Integer idTareaHabilidad){
        try{
            tarea_HabilidadService.deleteTareaHabilidad(idTareaHabilidad);
        }
        catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }



}