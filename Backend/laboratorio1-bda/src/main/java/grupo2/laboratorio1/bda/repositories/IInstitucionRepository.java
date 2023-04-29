package grupo2.laboratorio1.bda.repositories;

import java.util.List;

import grupo2.laboratorio1.bda.models.Institucion;

public interface IInstitucionRepository {
    public Institucion createInstitucion(Institucion institucion);
    public Institucion getInstitucion(Integer id_institucion);
    public List<Institucion> getAllInstituciones();
    public Institucion updateInstitucion(Institucion institucion);
    public boolean deleteInstitucion(Integer id_institucion);
}
