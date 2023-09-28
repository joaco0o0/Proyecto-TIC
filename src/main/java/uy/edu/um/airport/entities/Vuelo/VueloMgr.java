package uy.edu.um.airport.entities.Vuelo;

import org.springframework.beans.factory.annotation.Autowired;
import uy.edu.um.airport.persistence.VueloRepository;

public class VueloMgr {

    @Autowired
    private VueloRepository vueloRepository;

    public void addVuelo(Vuelo vuelo) {
        vueloRepository.save(vuelo);
    }
}
