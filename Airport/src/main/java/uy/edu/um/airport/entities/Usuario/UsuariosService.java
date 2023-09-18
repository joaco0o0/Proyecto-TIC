package uy.edu.um.airport.entities.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.Collectors;


@Service
public class UsuariosService {
    public List<Usuario> getUsuarios(){
        return List.of(
            new Usuario(
                    "Juan",
                    "Perez",
                    "juanperez@gmail.com",
                    "juanperez",
                    LocalDate.of(1990, 1, 1)
                    )
        );
    }
}
