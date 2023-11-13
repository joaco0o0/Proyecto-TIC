package uy.edu.um.airport.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uy.edu.um.airport.entities.Aeropuerto.Aeropuerto;
import uy.edu.um.airport.entities.Puerta.Puerta;
import uy.edu.um.airport.entities.Vuelo.Vuelo;

import java.util.List;

public interface AeropuertoRepository extends JpaRepository<Aeropuerto, String> {
    Aeropuerto findByCodigoIATA(String codigoIATA);

    boolean existsByNombre(String nombre);

    boolean existsByCodigoIATA(String codigoIATA);

    @Query("SELECT v FROM Vuelo v WHERE v.aeropuertoOrigen = :aeropuerto OR v.aeropuertoDestino = :aeropuerto")
    List<Vuelo> findAllVuelos(Aeropuerto aeropuerto);

    @Query("SELECT p FROM Puerta p WHERE p.terminal.aeropuerto = :aeropuerto")
    List<Puerta> findAllPuertas(Aeropuerto aeropuerto);
}
