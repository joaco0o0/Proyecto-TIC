package uy.edu.um.airport.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import uy.edu.um.airport.entities.AeropuertoYAerolinea.AeropuertoYAerolinea;
import uy.edu.um.airport.entities.AeropuertoYAerolinea.AeropuertoYAerolineaId;

public interface AeropuertoYAerolineaRepository extends JpaRepository<AeropuertoYAerolinea, AeropuertoYAerolineaId> {
}
