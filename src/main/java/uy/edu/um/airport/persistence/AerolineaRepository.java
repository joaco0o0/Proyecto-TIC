package uy.edu.um.airport.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import uy.edu.um.airport.entities.Aerolinea.Aerolinea;

public interface AerolineaRepository extends JpaRepository<Aerolinea, String> {

    Aerolinea findByCodigoIATA(String nombre);

    Aerolinea findByCodigoICAO(String nombre);

    Aerolinea findByNombre(String nombre);
}
