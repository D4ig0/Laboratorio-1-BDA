package grupo2.laboratorio1.bda.repositories;

import grupo2.laboratorio1.bda.models.Habilidad;

import java.util.List;

public interface IHabilidadRepository {
    void createHabilidad(Habilidad habilidad);
    Habilidad getHabilidad(Integer idHabilidad);
    List<Habilidad> getAllHabilidades();
    void updateHabilidad(Habilidad habilidad);
    void deleteHabilidad(Integer idHabilidad);
}
