package uy.edu.um.airport.entities.Aerolinea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uy.edu.um.airport.persistence.AerolineaRepository;

@Service
public class AerolineaMgr {

    @Autowired
    private AerolineaRepository aerolineaRepository;

    public void addAerolinea(Aerolinea aerolinea) {
        aerolineaRepository.save(aerolinea);
    }

    public Aerolinea findAerolineaByCodigoIATA(String codigoIATA) {
        return aerolineaRepository.findByCodigoIATA(codigoIATA);
    }

    public Aerolinea findAerolineaByCodigoICAO(String codigoICAO) {
        return aerolineaRepository.findByCodigoICAO(codigoICAO);
    }

    public Aerolinea findAerolineaByNombre(String nombre) {
        return aerolineaRepository.findByNombre(nombre);
    }

    public boolean existsByNombre(String nombre) {
        return aerolineaRepository.existsByNombre(nombre);
    }

    public boolean existsByCodigoIATA(String codigoIATA) {
        return aerolineaRepository.existsByCodigoIATA(codigoIATA);
    }

    public boolean existsByCodigoICAO(String codigoICAO) {
        return aerolineaRepository.existsByCodigoICAO(codigoICAO);
    }
}
