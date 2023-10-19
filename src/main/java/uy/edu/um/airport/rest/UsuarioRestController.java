package uy.edu.um.airport.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uy.edu.um.airport.entities.Usuario.Usuario;
import uy.edu.um.airport.entities.Usuario.UsuarioMgr;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioRestController {

    @Autowired
    private UsuarioMgr usuarioMgr;

    @GetMapping("/listar")
    public ResponseEntity<List<Usuario>> getUsuarios() {
        List<Usuario> usuarios = usuarioMgr.getUsuarios();
        return ResponseEntity.ok(usuarios);
    }

}
