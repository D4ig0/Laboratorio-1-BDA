package grupo2.laboratorio1.bda.Services;

import grupo2.laboratorio1.bda.models.Voluntario;
import grupo2.laboratorio1.bda.repositories.IVoluntarioRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoluntarioService {
    @Autowired
    IVoluntarioRepository voluntarioRepository;

    public void createVoluntario(@NonNull String nombre, @NonNull String correo, @NonNull String password){
        String encodedPasssword = generateEncodedPassword(password);
        Voluntario voluntario = new Voluntario(null, nombre, correo, encodedPasssword);
        voluntarioRepository.createVoluntario(voluntario);
    }

    public Voluntario getVoluntario(@NonNull Integer idVoluntario){
        if(!existsVoluntario(idVoluntario)){
            throw new IllegalArgumentException("No existe el voluntario");
        }
        return voluntarioRepository.getVoluntario(idVoluntario);
    }

    public List<Voluntario> getAllVoluntarios(){
        return voluntarioRepository.getAllVoluntarios();
    }

    public void updateVoluntario(Integer idVoluntario, Voluntario voluntario){
        if(!existsVoluntario(idVoluntario)){
            throw new IllegalArgumentException("No existe el voluntario");
        }

        String password = voluntario.getPassword();
        if(password != null){
            String encodedPasssword = generateEncodedPassword(voluntario.getPassword());
            voluntario.setPassword(encodedPasssword);
        }

        voluntario.setIdVoluntario(idVoluntario);
        voluntarioRepository.updateVoluntario(voluntario);
    }

    public void deleteVoluntario(Integer idVoluntario){
        if(!existsVoluntario(idVoluntario)){
            throw new IllegalArgumentException("No existe el voluntario");
        }

        voluntarioRepository.deleteVoluntario(idVoluntario);
    }

    public boolean existsVoluntario(Integer idVoluntario){
        return voluntarioRepository.existsVoluntario(idVoluntario);
    }

    private String generateEncodedPassword(String passsword){
        return voluntarioRepository.getEncodedPassword(passsword);
    }
}
