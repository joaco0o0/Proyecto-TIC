package uy.edu.um.airport.entities.Vuelo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uy.edu.um.airport.persistence.VueloRepository;

@Service
public class VueloMgr {
    @Autowired
    private VueloRepository vueloRepository;

    public void addVuelo(Vuelo vuelo) {
        vueloRepository.save(vuelo);
    }
     public void updateVuelo(Vuelo vuelo) {
        vueloRepository.save(vuelo);
    }
}
