package grupo2.laboratorio1.bda.controllers;

import grupo2.laboratorio1.bda.models.Institucion;
import grupo2.laboratorio1.bda.servicies.InstitucionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
