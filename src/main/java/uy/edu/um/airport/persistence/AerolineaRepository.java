package uy.edu.um.airport.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import uy.edu.um.airport.entities.Aerolinea.Aerolinea;

import java.util.Optional;

public interface AerolineaRepository extends JpaRepository<Aerolinea, Long> {
    public Optional<Aerolinea> findByNombre(String nombre);
}
