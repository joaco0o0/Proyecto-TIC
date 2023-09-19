package uy.edu.um.airport.ui.Usuario;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uy.edu.um.airport.entities.Usuario.Usuario;
import uy.edu.um.airport.entities.Usuario.UsuarioMgr;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/usuarios")
public class UsuarioController {

    private final UsuarioMgr usuariosService;

    public UsuarioController(UsuarioMgr usuariosService) {
        this.usuariosService = usuariosService;
    }

}
