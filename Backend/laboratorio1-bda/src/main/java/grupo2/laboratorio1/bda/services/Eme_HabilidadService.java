package grupo2.laboratorio1.bda.services;

import grupo2.laboratorio1.bda.models.Eme_Habilidad;
import grupo2.laboratorio1.bda.models.Emergencia;
import grupo2.laboratorio1.bda.repositories.Eme_HabilidadRepository;
import grupo2.laboratorio1.bda.repositories.EmergenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Eme_HabilidadService {
    @Autowired
    private Eme_HabilidadRepository eme_habilidadRepository;
    @Autowired
    private EmergenciaRepository emergenciaRepository;
    @Autowired
    private HabilidadRepository habilidadRepository;

    public void createEme_Habilidad(Integer idEmergencia, Integer idHabilidad){
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
        Eme_Habilidad eme_habilidad = new Eme_Habilidad(idEmergencia, idHabilidad);
        eme_habilidadRepository.createEme_Habilidad(eme_habilidad);
    }
}
