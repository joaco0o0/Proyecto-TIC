package uy.edu.um.airport.entities.Aeropuerto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uy.edu.um.airport.entities.Puerta.Puerta;
import uy.edu.um.airport.entities.Vuelo.Vuelo;
import uy.edu.um.airport.persistence.AeropuertoRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public List<Vuelo> getTodosLosVuelos(Aeropuerto aeropuerto) {
        return aeropuertoRepository.findAllVuelos(aeropuerto);
    }

    public List<Puerta> obtenerPuertas(Aeropuerto aeropuerto) {
        return aeropuertoRepository.findAllPuertas(aeropuerto);
    }
}
