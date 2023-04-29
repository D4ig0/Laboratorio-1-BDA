package grupo2.laboratorio1.bda.controllers;

import grupo2.laboratorio1.bda.models.VolEmergencia;
import grupo2.laboratorio1.bda.services.VolEmergenciaService;

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
public class VolEmergenciaController {
    @Autowired
    private VolEmergenciaService volEmergenciaService;

    @PostMapping("/volEmergencia")
    public void createVolEmergencia(@RequestParam("idVoluntario") Integer idVoluntario,
                                    @RequestParam("idEmergencia") Integer idEmergencia){
        try{
            volEmergenciaService.createVolEmergencia(idVoluntario, idEmergencia);
        }
        catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

    }

    @GetMapping("/volEmergencia/{id}")
    public VolEmergencia getVolEmergencia(@PathVariable("id") Integer id){
        try{
            return volEmergenciaService.getVolEmergencia(id);
        }
        catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("/volEmergencia/all")
    public List<VolEmergencia> getAllVolEmergencia(){
        try{
            return volEmergenciaService.getAllVolEmergencia();
        }
        catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PutMapping("/volEmergencia/all/{id}")
    public VolEmergencia updateVolEmergencia(@PathVariable("id") Integer id, @RequestBody VolEmergencia volEmergencia){
        try{
            return volEmergenciaService.updateVolEmergencia(id, volEmergencia);
        }
        catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @DeleteMapping("/volEmergencia/all/{id}")
    public void deleteVolEmergencia(@PathVariable("id") Integer id){
        try{
            volEmergenciaService.deleteVolEmergencia(id);
        }
        catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
