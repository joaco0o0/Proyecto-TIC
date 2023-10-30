package uy.edu.um.airport.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import uy.edu.um.airport.entities.Puerta.Puerta;

public interface PuertaRepository extends JpaRepository<Puerta, String> {
    // Métodos adicionales si los necesitas
}

