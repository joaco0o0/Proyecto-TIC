package uy.edu.um.airport.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uy.edu.um.airport.entities.Puerta.Puerta;

public interface PuertaRepository extends JpaRepository<Puerta, String> {

    @Query("SELECT p FROM Puerta p WHERE p.id = :id")
    Puerta findbyid(String id);
}

