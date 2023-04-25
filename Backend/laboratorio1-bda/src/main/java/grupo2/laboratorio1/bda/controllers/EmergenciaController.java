package grupo2.laboratorio1.bda.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import grupo2.laboratorio1.bda.models.Emergencia;
import grupo2.laboratorio1.bda.servicies.EmergenciaService;

@RestController
@CrossOrigin
public class EmergenciaController {
    @Autowired
    private EmergenciaService emergenciaService;

    @PostMapping("/emergencia/create")
    public Emergencia createEmergencia(@RequestParam String nombre, @RequestParam String descripcion, @RequestParam String fecha_inicio, @RequestParam String fecha_termino, @RequestParam String activo, @RequestParam Integer id_institucion){
        return emergenciaService.createEmergencia(nombre, descripcion, fecha_inicio, fecha_termino, activo, id_institucion);
    }

    @GetMapping("/emergencia/{id_emergencia}")
    public Emergencia getEmergencia(@PathVariable("id_emergencia") Integer id_emergencia){
        return emergenciaService.getEmergencia(id_emergencia);
    }

    @GetMapping("/emergencias")
    public List<Emergencia> getAllEmergencias(){
        return emergenciaService.getAllEmergencias();
    }

    @PutMapping("/emergencia/update")
    public Emergencia updateEmergencia(@RequestBody Emergencia emergencia){
        return emergenciaService.updateEmergencia(emergencia);
    }

    @DeleteMapping("/emergencia/delete/{id_emergencia}")
    public Boolean deleteEmergencia(@PathVariable("id_emergencia") Integer id_emergencia){
        return emergenciaService.deleteEmergencia(id_emergencia);
    }
}
