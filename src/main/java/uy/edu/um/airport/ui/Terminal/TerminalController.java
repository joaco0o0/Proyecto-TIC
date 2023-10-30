package uy.edu.um.airport.ui.Terminal;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uy.edu.um.airport.entities.Aeropuerto.Aeropuerto;
import uy.edu.um.airport.entities.Terminal.Terminal;
import uy.edu.um.airport.entities.Terminal.TerminalMgr;
import uy.edu.um.airport.entities.Usuario.Usuario;
import uy.edu.um.airport.session.Session;

@Component
public class TerminalController {

    @Autowired
    private TerminalMgr terminalMgr;

    @FXML
    private TextField txtTerminalNombre;

    @FXML
    public void handleRegisterTerminal() {
        String nombreTerminal = txtTerminalNombre.getText().trim();

        // Validaciones básicas
        if (nombreTerminal.isEmpty()) {
            System.err.println("El nombre de la terminal es obligatorio.");
            return;
        }

        // Comprobar si la terminal ya existe
        Terminal terminalExistente = terminalMgr.findTerminalById(nombreTerminal); // Suponiendo que tienes un método findTerminalById en TerminalMgr
        if (terminalExistente != null) {
            System.err.println("La terminal con este nombre ya existe.");
            return;
        }

        // Obtener el aeropuerto asociado al usuario logueado
        Usuario usuarioLogueado = Session.getInstance().getCurrentUser();
        Aeropuerto aeropuertoUsuario = usuarioLogueado.getAeropuerto();

        if (aeropuertoUsuario == null) {
            System.err.println("No se pudo obtener el aeropuerto asociado al usuario.");
            return;
        }

        // Crear y guardar la nueva terminal
        Terminal nuevaTerminal = new Terminal(nombreTerminal, null, aeropuertoUsuario);
        terminalMgr.addTerminal(nuevaTerminal); // Supongo que tienes un método addTerminal en TerminalMgr
    }


}
