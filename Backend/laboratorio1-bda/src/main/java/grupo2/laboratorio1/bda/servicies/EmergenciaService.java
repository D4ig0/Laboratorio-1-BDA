package grupo2.laboratorio1.bda.servicies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import grupo2.laboratorio1.bda.models.Emergencia;
import grupo2.laboratorio1.bda.repositories.IEmergenciaRepository;

@Service
public class EmergenciaService {
    @Autowired
    private IEmergenciaRepository emergenciaRepository;

    public Emergencia createEmergencia(Emergencia emergencia){
        return emergenciaRepository.createEmergencia(emergencia);
    }
}
