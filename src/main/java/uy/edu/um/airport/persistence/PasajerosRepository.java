package uy.edu.um.airport.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import uy.edu.um.airport.entities.Pasajeros.Pasajeros;
import uy.edu.um.airport.entities.Pasajeros.PasajerosId;

public interface PasajerosRepository extends JpaRepository<Pasajeros, PasajerosId> {
}
