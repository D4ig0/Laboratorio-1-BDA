package grupo2.laboratorio1.bda.services;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import grupo2.laboratorio1.bda.models.Emergencia;
import grupo2.laboratorio1.bda.repositories.IEmeHabilidadRepository;
import grupo2.laboratorio1.bda.repositories.IEmergenciaRepository;
import grupo2.laboratorio1.bda.repositories.ITareaHabilidadRepository;

@Service
public class EmergenciaService {
    @Autowired
    private IEmergenciaRepository emergenciaRepository;
    @Autowired
    private IEmeHabilidadRepository emeHabilidadRepository;
    @Autowired
    private ITareaHabilidadRepository tareaHabilidadRepository;

    public Emergencia createEmergencia(String nombre, String descripcion, String fecha_inicio, String fecha_termino, String activo, Integer id_institucion){
        Emergencia emergencia = new Emergencia();
        emergencia.setNombre(nombre);
        emergencia.setDescripcion(descripcion);
        emergencia.setFecha_inicio(Date.valueOf(fecha_inicio));
        emergencia.setFecha_termino(Date.valueOf(fecha_termino));
        emergencia.setActivo(Boolean.valueOf(activo));
        emergencia.setIdInstitucion(Integer.valueOf(id_institucion));
        return emergenciaRepository.createEmergencia(emergencia);
    }

    public Emergencia getEmergencia(Integer id_emergencia){
        return emergenciaRepository.getEmergencia(id_emergencia);
    }

    public List<Emergencia> getAllEmergencias(){
        return emergenciaRepository.getAllEmergencias();
    }

    public Emergencia updateEmergencia(Emergencia emergencia){
        return emergenciaRepository.updateEmergencia(emergencia);
    }

    public Boolean deleteEmergencia(Integer id_emergencia){
        if(emeHabilidadRepository.getEmeHabilidadByIdEmergencia(id_emergencia).size() > 0){
            throw new IllegalArgumentException("No se puede eliminar la emergencia porque tiene habilidades asociadas");
        }
        if(tareaHabilidadRepository.getTareaHabilidadByIdEmergencia(id_emergencia).size() > 0){
            throw new IllegalArgumentException("No se puede eliminar la emergencia porque tiene tareas asociadas");
        }
        return emergenciaRepository.deleteEmergencia(id_emergencia);
    }
}
