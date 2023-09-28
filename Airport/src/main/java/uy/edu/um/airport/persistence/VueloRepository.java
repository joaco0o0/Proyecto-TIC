package uy.edu.um.airport.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import uy.edu.um.airport.entities.Vuelo.Vuelo;

public interface VueloRepository extends JpaRepository<Vuelo, Long> {

}
