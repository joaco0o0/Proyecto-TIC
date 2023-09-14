package uy.edu.um.airport.entities.Usuario.uAirport;

import org.hibernate.id.uuid.LocalObjectUuidHelper;
import org.springframework.stereotype.Service;
import uy.edu.um.airport.entities.Usuario.Usuario;

import java.time.LocalDate;
import java.util.List;

@Service
public class UsuariosAeropuertoService {
    public List<UsuarioAeropuerto> getUsuariosAeropuerto(){
        return List.of(
            new UsuarioAeropuerto(
                "Juan",
                "Gomez",
                "juangomez@gmail.com",
                "1234",
                null,
                "AdministradorAerpuerto"
            ),
            new UsuarioAeropuerto(
                "Pedro",
                "Perez",
                "pedroperez@gmail.com",
                "4321",
                null,
                "Maletero"
            )
        );
    }
}
