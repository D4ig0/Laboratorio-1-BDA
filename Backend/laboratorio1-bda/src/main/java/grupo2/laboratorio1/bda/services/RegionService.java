package grupo2.laboratorio1.bda.services;

import grupo2.laboratorio1.bda.models.Region;
import grupo2.laboratorio1.bda.repositories.IRegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionService {
    @Autowired
    IRegionRepository regionRepository;

    public List<Region> getall(){
        return regionRepository.getAllRegiones();
    }
}
