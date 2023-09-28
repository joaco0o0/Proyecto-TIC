package uy.edu.um.airport.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import uy.edu.um.airport.entities.Equipaje.Equipaje;
import uy.edu.um.airport.entities.Usuario.Usuario;
import uy.edu.um.airport.entities.Vuelo.Vuelo;

public interface EquipajeRepository extends JpaRepository<Equipaje, Long> {

    Equipaje findByDescripcion(String descripcion);

    Equipaje findByEstado(String estado);

    Equipaje findByVuelo(Vuelo vuelo);

    Equipaje findByUsuario(Usuario usuario);

    Equipaje findByUsuarioAndVueloAndDescripcion(Usuario usuario, Vuelo vuelo, String descripcion);
}
