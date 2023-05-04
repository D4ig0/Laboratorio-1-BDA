package grupo2.laboratorio1.bda.controllers;

import grupo2.laboratorio1.bda.models.EmeHabilidad;
import grupo2.laboratorio1.bda.services.EmeHabilidadService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin
public class EmeHabilidadController {
    @Autowired
    private EmeHabilidadService emeHabilidadService;

    @PostMapping("/emeHabilidades")
    public void createEmeHabilidad(@RequestParam("idEmergencia") Integer idEmergencia,
                                    @RequestParam("idHabilidad") Integer idHabilidad){
        try{
            emeHabilidadService.createEmeHabilidad(idEmergencia, idHabilidad);
        }
        catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

    }

    @GetMapping("/emeHabilidades/{id}")
    public EmeHabilidad getEmeHabilidad(@PathVariable("id") Integer idEmeHabilidad){
        try{
            return emeHabilidadService.getEmeHabilidad(idEmeHabilidad);
        }
        catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
    
    @GetMapping("/emeHabilidades")
    public List<EmeHabilidad> getAllEmeHabilidad(){
        try{
            return emeHabilidadService.getAllEmeHabilidad();
        }
        catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PutMapping("/emeHabilidades/{id}")
    public EmeHabilidad updateEmeHabilidad(@PathVariable("id") Integer idEmeHabilidad, @RequestBody EmeHabilidad emehabilidad){
        try{
            return emeHabilidadService.updateEmeHabilidad(idEmeHabilidad,emehabilidad);
        }
        catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @DeleteMapping("/emeHabilidades/{id}")
    public void deleteEmeHabilidad(@PathVariable("id") Integer idEmeHabilidad){
        try{
            emeHabilidadService.deleteEmeHabilidad(idEmeHabilidad);
        }
        catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
