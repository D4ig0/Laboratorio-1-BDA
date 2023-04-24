package grupo2.laboratorio1.bda.repositories;

import grupo2.laboratorio1.bda.models.Voluntario;

import java.util.List;

public interface IVoluntarioRepository {
    void createVoluntario(Voluntario voluntario);
    Voluntario getVoluntario(Integer idVoluntario);
    List<Voluntario> getAllVoluntarios();
    void updateVoluntario(Voluntario ranking);
    boolean existsVoluntario(Integer idVoluntario);
    String getEncodedPassword(String password);
}

