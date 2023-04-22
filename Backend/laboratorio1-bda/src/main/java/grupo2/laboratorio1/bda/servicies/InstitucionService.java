package grupo2.laboratorio1.bda.servicies;

import java.util.List;

import org.springframework.stereotype.Service;

import grupo2.laboratorio1.bda.models.Institucion;
import grupo2.laboratorio1.bda.repositories.IInstitucionRepository;

@Service
public class InstitucionService {

    private final IInstitucionRepository institucionRepository;
    InstitucionService(IInstitucionRepository institucionRepository){
        this.institucionRepository = institucionRepository;
    }

    public List<Institucion> getAllInstituciones() {
        return institucionRepository.getAllInstituciones();
    }
    
}
