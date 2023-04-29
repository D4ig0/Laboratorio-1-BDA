package grupo2.laboratorio1.bda.services;

import grupo2.laboratorio1.bda.models.Voluntario;
import grupo2.laboratorio1.bda.repositories.IVoluntarioRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class VoluntarioService {
    @Autowired
    IVoluntarioRepository voluntarioRepository;

    public void createVoluntario(@NonNull String nombre, @NonNull String correo, @NonNull String password){
        String encodedPasssword = generateEncodedPassword(password);
        Voluntario voluntario = new Voluntario(null, nombre, correo, encodedPasssword);
        vaildateVoluntario(voluntario);
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
        voluntario.setIdVoluntario(idVoluntario);
        vaildateVoluntario(voluntario);

        String password = voluntario.getPassword();
        if(password != null){
            String encodedPasssword = generateEncodedPassword(voluntario.getPassword());
            voluntario.setPassword(encodedPasssword);
        }

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

    public boolean existsVoluntarioByCorreo(String correo){
        return voluntarioRepository.existsVoluntarioByCorreo(correo);
    }

    private String generateEncodedPassword(String passsword){
        return voluntarioRepository.getEncodedPassword(passsword);
    }

    private void vaildateVoluntario(Voluntario voluntario){
        Integer idVoluntario = voluntario.getIdVoluntario();
        String correo = voluntario.getCorreo();

        if(idVoluntario != null && !existsVoluntario(idVoluntario)){
            throw new IllegalArgumentException("No existe el voluntario");
        }

        if(correo != null){
            if(existsVoluntarioByCorreo(correo)){
                throw new IllegalArgumentException("El correo ingresado ya esta registrado");
            }

            if(!isValidCorreo(correo)){
                throw new IllegalArgumentException("El correo ingresado no es valido");
            }
        }
    }

    private boolean isValidCorreo(String correo){
        String regex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(correo);
        return matcher.matches();
    }
}
