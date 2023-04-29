package grupo2.laboratorio1.bda.services;

import grupo2.laboratorio1.bda.models.TareaHabilidad;
import grupo2.laboratorio1.bda.repositories.ITareaRepository;
import grupo2.laboratorio1.bda.repositories.IHabilidadRepository;
import grupo2.laboratorio1.bda.repositories.ITareaHabilidadRepository;
import grupo2.laboratorio1.bda.repositories.IEmergenciaRepository;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TareaHabilidadService {
    @Autowired
    private ITareaHabilidadRepository tareaHabilidadRepository;
    @Autowired
    private ITareaRepository tareaRepository;
    @Autowired
    private IHabilidadRepository habilidadRepository;
    @Autowired
    private IEmergenciaRepository emergenciaRepository;

    public void createTareaHabilidad(Integer idEmergencia, Integer idHabilidad, Integer idTarea){
        try{
            emergenciaRepository.getEmergencia(idEmergencia);
        } catch ( Exception e){
            throw new RuntimeException("No se ha encontrado en la base de datos emergencia");
        }
        try {
            habilidadRepository.getHabilidad(idHabilidad);
        } catch (Exception e ){
            throw new RuntimeException("No se ha encontrado en la base de datos habilidad");
        }
        try{
            tareaRepository.getTarea(idTarea);
        } catch ( Exception e){
            throw new RuntimeException("No se ha encontrado en la base de datos tarea");
        }
        TareaHabilidad tarea_habilidad = new TareaHabilidad(null, idEmergencia, idHabilidad,idTarea);
        tareaHabilidadRepository.createTareaHabilidad(tarea_habilidad);
    }


    public TareaHabilidad getTareaHabilidad(Integer idTareaHabilidad){
        try{
            return tareaHabilidadRepository.getTareaHabilidad(idTareaHabilidad);
        } catch (Exception e){
            throw new RuntimeException("No se ha encontrado en la base de datos  tarea_habilidad");
        }
    }

    public List<TareaHabilidad> getAllTareaHabilidad(){
        return tareaHabilidadRepository.getAllTareaHabilidad();
    }


    public TareaHabilidad updateTareaHabilidad(Integer idTareaHabilidad, TareaHabilidad tareaHabilidad){
        try{
            getTareaHabilidad(idTareaHabilidad);
        } catch (Exception e){
            throw new RuntimeException("No se ha encontrado en la base de datos  tarea_habilidad");
        }
        return tareaHabilidadRepository.updateTareaHabilidad(idTareaHabilidad, tareaHabilidad);
    }

    public void deleteTareaHabilidad(Integer idTareaHabilidad){

        try{
            getTareaHabilidad(idTareaHabilidad);
        } catch (Exception e){
            throw new RuntimeException("No se ha encontrado en la base de datos  tarea_habilidad");
        }

        tareaHabilidadRepository.deleteTareaHabilidad(idTareaHabilidad);
    }


}
