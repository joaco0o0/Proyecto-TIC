package uy.edu.um.airport.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import uy.edu.um.airport.entities.Aeropuerto.Aeropuerto;

public interface AeropuertoRepository extends JpaRepository<Aeropuerto, String> {
}
