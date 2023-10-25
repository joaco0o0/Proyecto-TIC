package uy.edu.um.airport.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import uy.edu.um.airport.entities.Aeropuerto.Aeropuerto;
import uy.edu.um.airport.entities.AeropuertoYAerolinea.AeropuertoYAerolinea;

public interface AeropuertoYAerolineaRepository extends JpaRepository<AeropuertoYAerolinea, String> {

}
