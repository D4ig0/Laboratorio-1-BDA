package grupo2.laboratorio1.bda.repositories;

import java.util.List;

import grupo2.laboratorio1.bda.models.Institucion;

public interface IInstitucionRepository {
    Institucion createInstitucion(Institucion institucion);
    Institucion getInstitucion(Integer id_institucion);
    List<Institucion> getAllInstituciones();
    Institucion updateInstitucion(Institucion institucion);
    boolean deleteInstitucion(Integer id_institucion);
}
