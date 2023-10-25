package uy.edu.um.airport.entities.Avion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uy.edu.um.airport.persistence.AvionRepository;

@Service
public class AvionMgr {

    @Autowired
    private AvionRepository avionRepository;

    public void addAvion(Avion avion) {
        avionRepository.save(avion);
    }

   // public Avion findAvionByModelo(String modelo) {
       // return avionRepository.findByModelo(modelo);
    //}
}
