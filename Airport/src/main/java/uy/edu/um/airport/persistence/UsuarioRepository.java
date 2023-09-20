package uy.edu.um.airport.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import uy.edu.um.airport.entities.Usuario.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Tus métodos personalizados aquí, por ejemplo, buscar por email o pasaporte
    Usuario findByEmail(String email);
    Usuario findByPasaporte(String pasaporte);
}


