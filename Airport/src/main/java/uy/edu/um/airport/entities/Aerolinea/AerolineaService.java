package uy.edu.um.airport.entities.Aerolinea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AerolineaService {
    /*@Autowired
    private AerolineaRepository aerolineaRepository;

    public Aerolinea getAerolinea(String nombre){
        return aerolineaRepository.getAerolinea(nombre);
    }*/
    public List<Aerolinea> getAerolineas(){
        return List.of(
            new Aerolinea("Pluna"),
            new Aerolinea("Aerolineas Argentinas"),
            new Aerolinea("Aeromexico")
        );
    }
}

