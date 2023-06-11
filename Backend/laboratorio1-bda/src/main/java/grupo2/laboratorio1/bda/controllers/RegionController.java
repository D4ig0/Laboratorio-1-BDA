package grupo2.laboratorio1.bda.controllers;

import grupo2.laboratorio1.bda.models.Region;
import grupo2.laboratorio1.bda.services.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/regiones")
public class RegionController {
    @Autowired
    RegionService regionService;

    @GetMapping()
    public ResponseEntity<List<Region>> getall(){
        return ResponseEntity.ok(regionService.getall());
    }

}
