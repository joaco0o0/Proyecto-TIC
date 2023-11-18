package uy.edu.um.airport.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import uy.edu.um.airport.entities.Vuelo.Vuelo;

import java.util.List;

public interface VueloRepository extends JpaRepository<Vuelo, Long> {

    List<Vuelo> findByEstadoVuelo(Vuelo.EstadoVuelo estadoVuelo);
    Vuelo findByNumeroVuelo(Long numeroVuelo);
}
