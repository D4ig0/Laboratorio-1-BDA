package grupo2.laboratorio1.bda.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import grupo2.laboratorio1.bda.models.Emergencia;
import grupo2.laboratorio1.bda.servicies.EmergenciaService;

@RestController
@CrossOrigin
public class EmergenciaController {
    @Autowired
    private EmergenciaService emergenciaService;

    @PostMapping("/emergencia/create")
    public Emergencia createEmergencia(@RequestBody Emergencia emergencia){
        return emergenciaService.createEmergencia(emergencia);
    }
    
}
