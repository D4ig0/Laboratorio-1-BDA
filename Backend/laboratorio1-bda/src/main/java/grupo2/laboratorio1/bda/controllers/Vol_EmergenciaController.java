package grupo2.laboratorio1.bda.controllers;

import grupo2.laboratorio1.bda.models.Vol_Emergencia;
import grupo2.laboratorio1.bda.services.Vol_EmergenciaService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin
public class Vol_EmergenciaController {
    @Autowired
    private Vol_EmergenciaService vol_emergenciaService;

    @PostMapping("/vol_emergencia")
    public void createVol_Emergencia(@RequestParam("idVoluntario") Integer idVoluntario,
                                    @RequestParam("idEmergencia") Integer idEmergencia){
        try{
            vol_emergenciaService.createVol_Emergencia(idVoluntario, idEmergencia);
        }
        catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

    }

    @GetMapping("/vol_emergencia/{id}")
    public Vol_Emergencia getVol_Emergencia(@PathVariable("id") Integer id){
        try{
            return vol_emergenciaService.getVol_Emergencia(id);
        }
        catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("/vol_emergencia/all")
    public List<Vol_Emergencia> getAllVol_Emergencia(){
        try{
            return vol_emergenciaService.getAllVol_Emergencia();
        }
        catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PutMapping("/vol_emergencia/all/{id}")
    public Vol_Emergencia updateVol_Emergencia(@PathVariable("id") Integer id, @RequestBody Vol_Emergencia vol_Emergencia){
        try{
            return vol_emergenciaService.updateVol_Emergencia(id, vol_Emergencia);
        }
        catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @DeleteMapping("/vol_emergencia/all/{id}")
    public void deleteVol_Emergencia(@PathVariable("id") Integer id){
        try{
            vol_emergenciaService.deleteVol_Emergencia(id);
        }
        catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
