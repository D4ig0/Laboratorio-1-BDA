package grupo2.laboratorio1.bda.controllers;

import grupo2.laboratorio1.bda.models.Eme_Habilidad;
import grupo2.laboratorio1.bda.services.Eme_HabilidadService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin
public class Eme_HabilidadController {
    @Autowired
    private Eme_HabilidadService eme_habilidadService;

    @PostMapping("/eme_habilidad")
    public void createEme_Habilidad(@RequestParam("idEmergencia") Integer idEmergencia,
                                    @RequestParam("idHabilidad") Integer idHabilidad){
        try{
            eme_habilidadService.createEme_Habilidad(idEmergencia, idHabilidad);
        }
        catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

    }

    @GetMapping("/eme_habilidad/{id}")
    public Eme_Habilidad getEme_Habilidad(@PathVariable("id") Integer idEmeHabilidad){
        try{
            return eme_habilidadService.getEme_Habilidad(idEmeHabilidad);
        }
        catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
    
    @GetMapping("/eme_habilidad/all")
    public List<Eme_Habilidad> getAllEme_Habilidad(){
        try{
            return eme_habilidadService.getAllEme_Habilidad();
        }
        catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
