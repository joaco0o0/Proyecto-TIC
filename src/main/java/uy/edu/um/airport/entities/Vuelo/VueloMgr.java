package uy.edu.um.airport.entities.Vuelo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uy.edu.um.airport.persistence.VueloRepository;

import java.util.List;

@Service
public class VueloMgr {
    @Autowired
    private VueloRepository vueloRepository;

    public List<Vuelo> getVuelosAceptados() {
        List<Vuelo> vuelos = vueloRepository.findByEstadoVuelo(Vuelo.EstadoVuelo.ACEPTADO);
        return vuelos;
    }

    public void addVuelo(Vuelo vuelo) {
        vueloRepository.save(vuelo);
    }
    public void updateVuelo(Vuelo vuelo) {
        vueloRepository.save(vuelo);
    }

    public Vuelo findVueloByCodigoVuelo(Long numeroVuelo) {
        Vuelo vuelo = vueloRepository.findByNumeroVuelo(numeroVuelo);
        return vuelo;
    }

    public List<Vuelo> findAll() {
        List<Vuelo> vuelos = vueloRepository.findAll();
        return vuelos;
    }
}
