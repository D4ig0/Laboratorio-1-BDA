package grupo2.laboratorio1.bda.controllers;

import grupo2.laboratorio1.bda.models.VolHabilidad;
import grupo2.laboratorio1.bda.services.VolHabilidadService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin
public class VolHabilidadController {
    @Autowired
    private VolHabilidadService volHabilidadService;

    @PostMapping("/volHabilidades")
    public void createVolHabilidad(@RequestParam("idVoluntario") Integer idVoluntario,
                                    @RequestParam("idHabilidad") Integer idHabilidad){
        try{
            volHabilidadService.createVolHabilidad(idVoluntario, idHabilidad);
        }
        catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

    }

    @GetMapping("/volHabilidades/{id}")
    public VolHabilidad getVolHabilidad(@PathVariable("id") Integer id){
        try{
            return volHabilidadService.getVolHabilidad(id);
        }
        catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("/volHabilidades")
    public List<VolHabilidad> getAllVolHabilidad(){
        try{
            return volHabilidadService.getAllVolHabilidad();
        }
        catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PutMapping("/volHabilidades/{id}")
    public VolHabilidad updateVolHabilidad(@PathVariable("id") Integer id, @RequestBody VolHabilidad volHabilidad){
        try{
            return volHabilidadService.updateVolHabilidad(id, volHabilidad);
        }
        catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @DeleteMapping("/volHabilidades/{id}")
    public void deleteVolHabilidad(@PathVariable("id") Integer id){
        try{
            volHabilidadService.deleteVolHabilidad(id);
        }
        catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
