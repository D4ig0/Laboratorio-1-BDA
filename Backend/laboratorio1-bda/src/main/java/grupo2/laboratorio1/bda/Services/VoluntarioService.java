package grupo2.laboratorio1.bda.Services;

import grupo2.laboratorio1.bda.models.Voluntario;
import grupo2.laboratorio1.bda.repositories.IVoluntarioRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoluntarioService {
    @Autowired
    IVoluntarioRepository voluntarioRepository;

    public void createVoluntario(@NonNull String nombre, @NonNull String correo, @NonNull String password){
        String encodedPasssword = generateEncodedPassword(password);
        Voluntario voluntario = new Voluntario(null, nombre, correo, encodedPasssword);
        voluntarioRepository.createVoluntario(voluntario);
    }

    private String generateEncodedPassword(String passsword){
        return voluntarioRepository.getEncodedPassword(passsword);
    }
}
