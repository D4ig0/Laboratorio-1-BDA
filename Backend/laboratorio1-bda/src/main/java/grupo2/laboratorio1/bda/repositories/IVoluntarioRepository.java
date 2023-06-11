package grupo2.laboratorio1.bda.repositories;

import grupo2.laboratorio1.bda.models.Voluntario;

import java.util.List;
import java.util.Optional;

public interface IVoluntarioRepository {
    void createVoluntario(Voluntario voluntario, Double longitud, Double latitud);
    Voluntario getVoluntario(Integer idVoluntario);
    Optional<Voluntario> getVoluntarioByCorreo(String correo);
    List<Voluntario> getAllVoluntarios();
    void updateVoluntario(Voluntario ranking);
    void deleteVoluntario(Integer idVoluntario);
    boolean existsVoluntario(Integer idVoluntario);
    boolean existsVoluntarioByCorreo(String correo);
    List<Voluntario> findVoluntarioForEmergencia(Double radio, Integer idEmergencia);
}

