package grupo2.laboratorio1.bda.repositories;

import grupo2.laboratorio1.bda.models.Voluntario;

public interface IVoluntarioRepository {
    void createVoluntario(Voluntario voluntario);
    String getEncodedPassword(String password);
}

