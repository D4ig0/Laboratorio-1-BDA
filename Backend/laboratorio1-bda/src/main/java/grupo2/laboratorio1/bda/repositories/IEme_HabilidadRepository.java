package grupo2.laboratorio1.bda.repositories;

import java.util.List;

import grupo2.laboratorio1.bda.models.Eme_Habilidad;

public interface IEme_HabilidadRepository {

    void createEme_Habilidad(Eme_Habilidad eme_habilidad);
    Eme_Habilidad getEme_Habilidad(Integer idEme_Habilidad);
    List<Eme_Habilidad> getAllEme_Habilidad();
}
