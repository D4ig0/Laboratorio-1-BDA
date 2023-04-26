package grupo2.laboratorio1.bda.services;

import grupo2.laboratorio1.bda.models.Tarea_Habilidad;
import grupo2.laboratorio1.bda.models.Tarea;
import grupo2.laboratorio1.bda.models.Habilidad;

import grupo2.laboratorio1.bda.repositories.ITareaRepository;
import grupo2.laboratorio1.bda.repositories.IHabilidadRepository;
import grupo2.laboratorio1.bda.repositories.IEmergenciaRepository;
import grupo2.laboratorio1.bda.repositories.ITarea_HabilidadRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Tarea_HabilidadService {
    @Autowired
    private ITarea_HabilidadRepository tarea_HabilidadRepository;
    @Autowired
    private ITareaRepository tareaRepository;
    @Autowired
    private IHabilidadRepository habilidadRepository;
    @Autowired
    private IEmergenciaRepository emergenciaRepository;

    public void createTarea_Habilidad(Integer idEmergencia, Integer idHabilidad, Integer idTarea){
        try{
            getEmergencia(idEmergencia);
        } catch ( Exception e){
            throw new RuntimeException("No se ha encontrado en la base de datos la Emergencia");
        }
        try {
            getHabilidad(idHabilidad);
        } catch (Exception e ){
            throw new RuntimeException("No se ha encontrado en la base de datos la Habilidad");
        }
        try{
            getTarea(idTarea);
        } catch ( Exception e){
            throw new RuntimeException("No se ha encontrado en la base de datos la Tarea");
        }
        Tarea_Habilidad tarea_habilidad = new Tarea_Habilidad(null, idEmergencia, idHabilidad,idTarea);
        tarea_HabilidadRepository.createTarea_Habilidad(tarea_habilidad);
    }


    public Tarea_Habilidad getTarea_Habilidad(Integer idTareaHabilidad){
        try{
            tarea_HabilidadRepository.getTarea_Habilidad(idTareaHabilidad);
        } catch (Exception e){
            throw new RuntimeException("No se ha encontrado en la base de datos la Tarea_Habilidad");
        }
    }

    public List<Tarea_Habilidad> getAllTarea_Habilidad(){
        return tarea_HabilidadRepository.getAllTarea_Habilidad();
    }


    public Tarea_Habilidad updateTarea_Habilidad(Integer idTareaHabilidad, Tarea_Habilidad tarea_habilidad){
        try{
            getTarea_Habilidad(idTareaHabilidad);
        } catch (Exception e){
            throw new RuntimeException("No se ha encontrado en la base de datos la Tarea_Habilidad");
        }
        tarea_HabilidadRepository.updateTarea_Habilidad(idTareaHabilidad, tarea_habilidad);
    }


}
