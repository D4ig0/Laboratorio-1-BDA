package grupo2.laboratorio1.bda.controllers;

import grupo2.laboratorio1.bda.services.Eme_HabilidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
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
}
