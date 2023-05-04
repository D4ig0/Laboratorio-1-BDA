package grupo2.laboratorio1.bda.controllers;

import grupo2.laboratorio1.bda.models.Institucion;
import grupo2.laboratorio1.bda.services.InstitucionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class InstitucionController {
    @Autowired
    InstitucionService institucionService;

    @GetMapping("/instituciones")
    public List<Institucion> getAllInstituciones(){
        return institucionService.getAllInstituciones();
    }

    @GetMapping("/institucion/{id}")
    public Institucion getInstitucion(@PathVariable("id") Integer id){
        return institucionService.getInstitucion(id);
    }

    @PostMapping("/institucion/create")
    public Institucion createInstitucion(@RequestParam String nombre){
        return institucionService.createInstitucion(nombre);
    }

    @PutMapping("/institucion/update")
    public Institucion updateInstitucion(@RequestBody Institucion institucion){
        return institucionService.updateInstitucion(institucion);
    }

    @DeleteMapping("/institucion/delete/{id}")
    public boolean deleteInstitucio(@PathVariable("id") Integer id){
        return institucionService.deleteInstitucion(id);
    }
}
