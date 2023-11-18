package uy.edu.um.airport.entities.Pasajeros;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uy.edu.um.airport.persistence.PasajerosRepository;

@Service
public class PasajerosMgr {

    @Autowired
    private PasajerosRepository pasajerosRepository;

    public void addPasajero(Pasajeros pasajero) {
        pasajerosRepository.save(pasajero);
    }
}
