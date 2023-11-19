package uy.edu.um.airport.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import uy.edu.um.airport.entities.Terminal.Terminal;

public interface TerminalRepository extends JpaRepository<Terminal, String> {

}
