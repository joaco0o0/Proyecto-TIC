package uy.edu.um.airport.entities.Aeropuerto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uy.edu.um.airport.persistence.AeropuertoRepository;

@Service
public class AeropuertoMgr {

    @Autowired
    private AeropuertoRepository aeropuertoRepository;

    public void addAeropuerto(Aeropuerto aeropuerto) {
        aeropuertoRepository.save(aeropuerto);
    }

    public Aeropuerto findAeropuertoByCodigoIATA(String codigoIATA) {
        return aeropuertoRepository.findByCodigoIATA(codigoIATA);
    }

    public boolean existsByNombre(String nombre) {
        return aeropuertoRepository.existsByNombre(nombre);
    }

    public boolean existsByCodigoIATA(String codigoIATA) {
        return aeropuertoRepository.existsByCodigoIATA(codigoIATA);
    }
}
