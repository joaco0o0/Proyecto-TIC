package uy.edu.um.airport.entities.AeropuertoYAerolinea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uy.edu.um.airport.entities.Aeropuerto.Aeropuerto;
import uy.edu.um.airport.persistence.AerolineaRepository;
import uy.edu.um.airport.persistence.AeropuertoYAerolineaRepository;

@Service
public class AeropuertoYAerolineaMgr {

    @Autowired
    private AeropuertoYAerolineaRepository aeropuertoYAerolineaRepository;

    public void addAsociacion(AeropuertoYAerolinea asociacion) {
        aeropuertoYAerolineaRepository.save(asociacion);
    }

}
