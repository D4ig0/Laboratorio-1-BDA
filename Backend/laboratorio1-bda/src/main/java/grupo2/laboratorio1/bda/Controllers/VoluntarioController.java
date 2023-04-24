package grupo2.laboratorio1.bda.Controllers;

import grupo2.laboratorio1.bda.Services.VoluntarioService;
import grupo2.laboratorio1.bda.models.Voluntario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
public class VoluntarioController {
    @Autowired
    VoluntarioService voluntarioService;

    @PostMapping("/voluntarios")
    public void createRanking(@RequestParam String nombre, @RequestParam String correo, @RequestParam String password){
        try{
            voluntarioService.createVoluntario(nombre, correo, password);
        }
        catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("/voluntarios/{id}")
    public Voluntario getVoluntario(@PathVariable("id") Integer idVoluntario){
        try {
            return voluntarioService.getVoluntario(idVoluntario);
        }
        catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("/voluntarios")
    public List<Voluntario> getAllVoluntarios(){
        return voluntarioService.getAllVoluntarios();
    }

    @PutMapping("/voluntarios/{id}")
    public void updateVoluntario(@PathVariable("id") Integer idVoluntario, @RequestBody Voluntario voluntario){
        try {
            voluntarioService.updateVoluntario(idVoluntario, voluntario);
        }
        catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
