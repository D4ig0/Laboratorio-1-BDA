package grupo2.laboratorio1.bda.controllers;

import grupo2.laboratorio1.bda.models.Habilidad;
import grupo2.laboratorio1.bda.services.HabilidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
public class HabilidadController {
    @Autowired
    HabilidadService habilidadService;

    @PostMapping("/habilidades")
    public void createHabilidad(@RequestParam Integer idHabilidad,
                              @RequestParam String descripcion){
        try{
            habilidadService.createHabilidad(idHabilidad, descripcion);
        }
        catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("/habilidades/{id}")
    public Habilidad getHabilidad(@PathVariable("id") Integer idHabilidad){
        try {
            return habilidadService.getHabilidad(idHabilidad);
        }
        catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("/habilidades")
    public List<Habilidad> getAllHabilidades(){
        return habilidadService.getAllHabilidades();
    }

    @PutMapping("/voluntarios/{id}")
    public void updateHabilidad(@PathVariable("id") Integer idHabilidad, @RequestBody Habilidad habilidad){
        try {
            habilidadService.updateHabilidad(idHabilidad, habilidad);
        }
        catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @DeleteMapping("/habilidades/{id}")
    public void deleteHabilidad(@PathVariable("id") Integer idHabilidad){
        try {
            habilidadService.deleteHabilidad(idHabilidad);
        }
        catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
