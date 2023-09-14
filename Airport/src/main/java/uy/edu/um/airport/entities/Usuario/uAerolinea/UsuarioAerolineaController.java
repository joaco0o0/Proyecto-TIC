package uy.edu.um.airport.entities.Usuario.uAerolinea;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/usuarios/aerolinea")
public class UsuarioAerolineaController {
    private final UsuarioAerolineaService usuarioAerolineaService;

    public UsuarioAerolineaController(UsuarioAerolineaService usuarioAerolineaService) {
        this.usuarioAerolineaService = usuarioAerolineaService;
    }

    public List<UsuarioAerolinea> getUsuariosAerolinea(){
        return usuarioAerolineaService.getUsuariosAerolinea();
    }
}
