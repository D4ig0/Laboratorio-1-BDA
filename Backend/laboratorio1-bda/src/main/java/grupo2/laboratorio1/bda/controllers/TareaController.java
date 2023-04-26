package grupo2.laboratorio1.bda.controllers;

import grupo2.laboratorio1.bda.models.Tarea;
import grupo2.laboratorio1.bda.services.TareaService;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;

@RestController
@CrossOrigin
public class TareaController {
    @Autowired
    TareaService tareaService;

    @PostMapping("/tarea")
    public void createRanking(@RequestParam Integer idEmergencia,
                              @RequestParam String nombre,
                              @RequestParam String descripcion,
                              @RequestParam Integer cantVolRequeridos,
                              @RequestParam Integer cantVolInscritos, 
                              @RequestParam Date fechaInicio,
                              @RequestParam Date fechaFin,
                              @RequestParam String estadoActual){
        try{
            tareaService.createTarea(null,idEmergencia, nombre, descripcion,cantVolRequeridos, cantVolInscritos, fechaInicio, fechaFin, estadoActual);
        }
        catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}