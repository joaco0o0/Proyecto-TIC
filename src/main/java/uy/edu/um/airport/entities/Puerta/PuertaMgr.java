package uy.edu.um.airport.entities.Puerta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uy.edu.um.airport.persistence.PuertaRepository;

@Service
public class PuertaMgr {

    @Autowired
    private PuertaRepository puertaRepository;

    public void addPuerta(Puerta puerta) {
        puertaRepository.save(puerta);
    }

    public Puerta findPuertaById(String id) {
        return puertaRepository.findById(id).orElse(null);
    }

    // Más métodos según tus necesidades
}
