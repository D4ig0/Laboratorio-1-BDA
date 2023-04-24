package grupo2.laboratorio1.bda.Controllers;

import grupo2.laboratorio1.bda.Services.VoluntarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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
}
