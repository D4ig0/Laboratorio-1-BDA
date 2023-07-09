package grupo2.laboratorio1.bda.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import grupo2.laboratorio1.bda.models.Institucion;
import grupo2.laboratorio1.bda.repositories.InstitucionRepository;

@Service
public class InstitucionService {

    @Autowired
    InstitucionRepository institucionRepository;

    public List<Institucion> getAllInstituciones() {
        return institucionRepository.getAllInstituciones();
    }

    public Institucion getInstitucion(Integer id_institucion){
        return institucionRepository.getInstitucion(id_institucion);
    }

    public void createInstitucion(String nombre){
        Institucion institucion = new Institucion();
        institucion.setNombre(nombre);
        institucionRepository.createInstitucion(institucion);
    }

    public Institucion updateInstitucion(Integer id, Institucion institucion){
        institucion.setIdInstitucion(id);
        return institucionRepository.updateInstitucion(institucion);
    }

    public boolean deleteInstitucion(Integer id_institucion){
        return institucionRepository.deleteInstitucion(id_institucion);
    }

}
