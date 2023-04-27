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

    public Habilidad getHabilidad(@NonNull Integer idHabilidad){
        //if(!existsHabilidad(idHabilidad)){
          //  throw new IllegalArgumentException("No existe la habilidad");
        //}
        return habilidadRepository.getHabilidad(idHabilidad);
    }

    public List<Habilidad> getAllHabilidades(){
        return habilidadRepository.getAllHabilidades();
    }

    public void updateHabilidad(Integer idHabilidad, Habilidad habilidad){
        habilidad.setIdHabilidad(idHabilidad);
        //vaildateHabilidad(habilidad);

        habilidadRepository.updateHabilidad(habilidad);
    }

    public void deleteHabilidad(Integer idHabilidad){
        //if(!existsHabilidad(idHabilidad)){
        //    throw new IllegalArgumentException("No existe la habilidad");
        //}

        habilidadRepository.deleteHabilidad(idHabilidad);
    }
}
