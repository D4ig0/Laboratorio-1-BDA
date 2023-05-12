package grupo2.laboratorio1.bda.controllers;

import grupo2.laboratorio1.bda.models.Institucion;
import grupo2.laboratorio1.bda.services.InstitucionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/instituciones")
public class InstitucionController {
    @Autowired
    InstitucionService institucionService;

    @GetMapping
    public List<Institucion> getAllInstituciones(){
        return institucionService.getAllInstituciones();
    }

    @GetMapping("/{id}")
    public Institucion getInstitucion(@PathVariable("id") Integer id){
        return institucionService.getInstitucion(id);
    }

    @PostMapping
    public Institucion createInstitucion(@RequestParam String nombre){
        return institucionService.createInstitucion(nombre);
    }

    @PutMapping("/{id}")
    public Institucion updateInstitucion(@PathVariable("id") Integer id,  @RequestBody Institucion institucion){
        return institucionService.updateInstitucion(id, institucion);
    }

    @DeleteMapping("/{id}")
    public boolean deleteInstitucio(@PathVariable("id") Integer id){
        return institucionService.deleteInstitucion(id);
    }
}
