package uy.edu.um.airport.entities.Usuario.uAirport;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/usuarios/aeropuerto")
public class UsuarioAeropuertoController {
    private final UsuariosAeropuertoService usuarioAeropuertoService;

    public UsuarioAeropuertoController(UsuariosAeropuertoService usuarioAeropuertoService) {
        this.usuarioAeropuertoService = usuarioAeropuertoService;
    }

    public List<UsuarioAeropuerto> getUsuariosAeropuerto(){
        return usuarioAeropuertoService.getUsuariosAeropuerto();
    }
}
