package uy.edu.um.airport.entities.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uy.edu.um.airport.entities.Usuario.Usuario;
import uy.edu.um.airport.persistence.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioMgr {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void addUsuario(Usuario usuario) {
        // Aquí podrías incluir lógica adicional para verificar los datos del usuario
        usuarioRepository.save(usuario);
    }

    public void deleteUsuario(Usuario usuario) {
        usuarioRepository.delete(usuario);
    }

    public Usuario findUsuarioByEmail(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        return usuario;
    }

    public int getCantidadDeUsuarios() {
        return (int) usuarioRepository.count();
    }

    public List<Usuario> getUsuarios() {
        return usuarioRepository.findAll();
    }
}
