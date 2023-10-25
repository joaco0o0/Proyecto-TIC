package uy.edu.um.airport.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import uy.edu.um.airport.entities.Avion.Avion;

public interface AvionRepository extends JpaRepository<Avion, String> {
   // Avion findByModelo(String modelo);
}
