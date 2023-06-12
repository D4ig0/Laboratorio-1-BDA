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
    IEmergenciaRepository emergenciaRepository;
    @Autowired
    IEmeHabilidadRepository emeHabilidadRepository;
    @Autowired
    ITareaHabilidadRepository tareaHabilidadRepository;

    public Emergencia createEmergencia(String nombre,
                                       String descripcion,
                                       String fecha_inicio,
                                       String fecha_termino,
                                       String activo,
                                       Integer id_institucion,
                                       double latitud,
                                       double longitud){
        Emergencia emergencia = new Emergencia();
        emergencia.setNombre(nombre);
        emergencia.setDescripcion(descripcion);
        emergencia.setFecha_inicio(Date.valueOf(fecha_inicio));
        emergencia.setFecha_termino(Date.valueOf(fecha_termino));
        emergencia.setActivo(Boolean.valueOf(activo));
        emergencia.setIdInstitucion(Integer.valueOf(id_institucion));
        return emergenciaRepository.createEmergencia(emergencia, latitud, longitud);
    }

    public Emergencia getEmergencia(Integer id_emergencia){
        return emergenciaRepository.getEmergencia(id_emergencia);
    }

    public List<Emergencia> getAllEmergencias(){
        return emergenciaRepository.getAllEmergencias();
    }

    public Emergencia updateEmergencia(Integer id, Emergencia emergencia){
        emergencia.setIdEmergencia(id);
        return emergenciaRepository.updateEmergencia(emergencia);
    }

    public Boolean deleteEmergencia(Integer id_emergencia){
        if(emeHabilidadRepository.getEmeHabilidadByIdEmergencia(id_emergencia).size() > 0){
            throw new IllegalArgumentException("No se puede eliminar la emergencia porque tiene habilidades asociadas");
        }
        return emergenciaRepository.deleteEmergencia(id_emergencia);
    }

    public List<Emergencia> getAllEmergenciasExtraData(){
        return emergenciaRepository.getAllEmergenciasExtraData();
    }

}
