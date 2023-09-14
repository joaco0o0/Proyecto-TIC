package uy.edu.um.airport.entities.Usuario.uAirport;

import uy.edu.um.airport.entities.Usuario.Usuario;

import java.time.LocalDate;

public class UsuarioAeropuerto extends Usuario {
    public String rol;
    public UsuarioAeropuerto(String nombre, String apellido, String email, String password, LocalDate fechanacimiento, String rol) {
        super(nombre, apellido, email, password, null);
        this.rol = rol;
    }
}
