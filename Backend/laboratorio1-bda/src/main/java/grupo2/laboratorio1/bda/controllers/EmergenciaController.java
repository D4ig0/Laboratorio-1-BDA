package grupo2.laboratorio1.bda.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import grupo2.laboratorio1.bda.models.Emergencia;
import grupo2.laboratorio1.bda.services.EmergenciaService;

@RestController
@CrossOrigin
@RequestMapping("/emergencias")
public class EmergenciaController {
    @Autowired
    private EmergenciaService emergenciaService;

    @PostMapping
    public Emergencia createEmergencia(@RequestParam String nombre,
                                       @RequestParam String descripcion,
                                       @RequestParam String fecha_inicio,
                                       @RequestParam String fecha_termino,
                                       @RequestParam String activo,
                                       @RequestParam Integer id_institucion,
                                       @RequestParam double latitud,
                                       @RequestParam double longitud){
        return emergenciaService.createEmergencia(nombre, descripcion, fecha_inicio, fecha_termino, activo, id_institucion, latitud, longitud);
    }

    @GetMapping("/{id}")
    public Emergencia getEmergencia(@PathVariable("id") Integer id_emergencia){
        return emergenciaService.getEmergencia(id_emergencia);
    }

    @GetMapping
    public List<Emergencia> getAllEmergencias(){
        return emergenciaService.getAllEmergencias();
    }

    @PutMapping("/{id}")
    public Emergencia updateEmergencia(@PathVariable("id") Integer id, @RequestBody Emergencia emergencia){
        return emergenciaService.updateEmergencia(id, emergencia);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteEmergencia(@PathVariable("id") Integer id_emergencia){
        return emergenciaService.deleteEmergencia(id_emergencia);
    }

    @GetMapping("/extra")
    public List<Emergencia> getAllEmergenciasExtraData(){
        return emergenciaService.getAllEmergenciasExtraData();
    }
}
