package grupo2.laboratorio1.bda.controllers;

import java.util.List;

import grupo2.laboratorio1.bda.models.Institucion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity createEmergencia(@RequestParam String nombre,
                                       @RequestParam String descripcion,
                                       @RequestParam String fecha_inicio,
                                       @RequestParam String fecha_termino,
                                       @RequestParam String activo,
                                       @RequestParam Integer id_institucion,
                                       @RequestParam double latitud,
                                       @RequestParam double longitud){
        try{
            Emergencia emergencia = emergenciaService.createEmergencia(nombre, descripcion, fecha_inicio, fecha_termino, activo, id_institucion, latitud, longitud);
            return ResponseEntity.ok(emergencia);
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Emergencia> getEmergencia(@PathVariable("id") Integer id_emergencia){
        return ResponseEntity.ok(emergenciaService.getEmergencia(id_emergencia));
    }

    @GetMapping
    public ResponseEntity<List<Emergencia>> getAllEmergencias(){
        return ResponseEntity.ok(emergenciaService.getAllEmergencias());
    }

    @PutMapping("/{id}")
    public ResponseEntity updateEmergencia(@PathVariable("id") Integer id, @RequestBody Emergencia emergencia){
        try {
            Emergencia emergenciaActualizada = emergenciaService.updateEmergencia(id, emergencia);
            return ResponseEntity.ok(emergenciaActualizada);
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEmergencia(@PathVariable("id") Integer id_emergencia){
        emergenciaService.deleteEmergencia(id_emergencia);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/extra")
    public ResponseEntity<List<Emergencia>> getAllEmergenciasExtraData(){
        try {
            List<Emergencia> totalEmergencias = emergenciaService.getAllEmergenciasExtraData();
            return ResponseEntity.ok(totalEmergencias);
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.notFound().header("message", e.getMessage()).build();
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();
        }
    }
}
