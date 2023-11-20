package uy.edu.um.airport.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uy.edu.um.airport.entities.Aerolinea.Aerolinea;
import uy.edu.um.airport.entities.Aeropuerto.Aeropuerto;
import uy.edu.um.airport.entities.Vuelo.Vuelo;

import java.util.List;

public interface AerolineaRepository extends JpaRepository<Aerolinea, String> {

    Aerolinea findByCodigoIATA(String nombre);

    Aerolinea findByCodigoICAO(String nombre);

    Aerolinea findByNombre(String nombre);

    boolean existsByNombre(String nombre);

    boolean existsByCodigoIATA(String codigoIATA);

    boolean existsByCodigoICAO(String codigoICAO);

    @Query("SELECT v FROM Vuelo v WHERE v.aerolineaIATA = :aerolinea")
    List<Vuelo> findAllVuelos(Aerolinea aerolinea);
}
