package uy.edu.um.airport.entities.Usuario.uAerolinea;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioAerolineaService {
    public List<UsuarioAerolinea> getUsuariosAerolinea(){
        return List.of(
            new UsuarioAerolinea(
                    "Marcos",
                    "Pereyra",
                    "marquitogamer@hotmail.com",
                    "marquitos",
                    null,
                    "AdminAerolinea",
                    "Pluna"
            ),
            new UsuarioAerolinea(
                    "Priscila",
                    "Ramos",
                    "lapribolso56@yahoo.com",
                    "manyagallina",
                    null,
                    "OperadorChekIn",
                    "Pluna"
            )
        );
    }
}
