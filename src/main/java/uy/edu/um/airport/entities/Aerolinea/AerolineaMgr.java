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

    public Aerolinea findAerolineaByNombre(String nombre) {
        return aerolineaRepository.findByNombre(nombre);
    }


}
