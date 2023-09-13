package uy.edu.um.airport.entities.Usuario;

import java.time.LocalDate;
import java.util.List;

public class UsuariosService {

    public List<Usuario> getUsuarios(){
        return List.of(
                new Usuario(
                        "Putito",
                        "Perez",
                        "putitoperez@gmail.com",
                        "melacomo",
                        LocalDate.of(1999, 12, 12)
                )
        );
    }
}
