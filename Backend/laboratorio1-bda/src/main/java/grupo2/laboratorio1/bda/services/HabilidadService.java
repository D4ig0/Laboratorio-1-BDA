package grupo2.laboratorio1.bda.services;

import grupo2.laboratorio1.bda.models.Habilidad;
import grupo2.laboratorio1.bda.repositories.HabilidadRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabilidadService {
    @Autowired
    HabilidadRepository habilidadRepository;

    public void createHabilidad(@NonNull Integer idHabilidad,
                              String descripcion){
        Habilidad habilidad = new Habilidad(idHabilidad, descripcion);
        habilidadRepository.createHabilidad(habilidad);
    }

}
