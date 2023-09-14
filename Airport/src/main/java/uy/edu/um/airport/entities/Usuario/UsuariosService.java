package uy.edu.um.airport.entities.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uy.edu.um.airport.entities.Usuario.uAerolinea.UsuarioAerolineaService;
import uy.edu.um.airport.entities.Usuario.uAirport.UsuariosAeropuertoService;

import java.util.List;
import java.util.stream.Stream;
import java.util.stream.Collectors;


@Service
public class UsuariosService {
    @Autowired
    private UsuariosAeropuertoService UsuarioaeropuertoService;

    @Autowired
    private UsuarioAerolineaService UsuarioaerolineaService;

    public List<Usuario> getUsuarios(){
        return Stream.concat(UsuarioaerolineaService.getUsuariosAerolinea().stream(), UsuarioaeropuertoService.getUsuariosAeropuerto().stream())
                .collect(Collectors.toList());
    }
}
