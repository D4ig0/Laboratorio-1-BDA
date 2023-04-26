package grupo2.laboratorio1.bda.controllers;

import grupo2.laboratorio1.bda.models.Tarea_Habilidad;
import grupo2.laboratorio1.bda.services.Tarea_HabilidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
public class Tarea_HabilidadController {
    @Autowired
    Tarea_HabilidadService tarea_HabilidadService;

    @PostMapping("/tarea_habilidades")
    public void createTarea_Habilidad(@RequestParam Integer idEmergencia, @RequestParam Integer idHabilidad, @RequestParam Integer idTarea){
        try{
            tarea_HabilidadService.createTarea_Habilidad(idEmergencia,  idHabilidad, idTarea);
        }
        catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }


    @GetMapping("/tarea_habilidades/{id}")
    public Tarea_Habilidad getTarea_Habilidad(@PathVariable("id") Integer idTarea_Habilidad){
        try{
            return tarea_HabilidadService.getTarea_Habilidad(idTarea_Habilidad);
        }
        catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
    
    @GetMapping("/tarea_habilidades/all")
    public List<Tarea_Habilidad> getAllTarea_Habilidad(){
        try{
            return tarea_HabilidadService.getAllTarea_Habilidad();
        }
        catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }








}