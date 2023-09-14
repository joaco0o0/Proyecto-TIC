package uy.edu.um.airport.entities.Usuario.uAerolinea;

import uy.edu.um.airport.entities.Usuario.Usuario;

import java.time.LocalDate;

public class UsuarioAerolinea extends Usuario {
    public String rol;
    public String aerolinea;
    public UsuarioAerolinea(String nombre, String apellido, String email, String password, LocalDate fechanacimiento, String rol, String aerolinea) {
        super(nombre, apellido, email, password, null);
        this.rol = rol;
        this.aerolinea = aerolinea;
    }
}
