package uy.edu.um.airport.ui.Puerta;

import javafx.fxml.FXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uy.edu.um.airport.entities.Aerolinea.Aerolinea;
import uy.edu.um.airport.entities.Puerta.Puerta;
import uy.edu.um.airport.entities.Puerta.PuertaMgr;
import uy.edu.um.airport.entities.Aeropuerto.Aeropuerto;
import uy.edu.um.airport.entities.Terminal.Terminal;
import uy.edu.um.airport.entities.Terminal.TerminalMgr;
import uy.edu.um.airport.entities.Usuario.Usuario;
import uy.edu.um.airport.session.Session;

@Component
public class PuertaController {

    @Autowired
    private PuertaMgr puertaMgr;

    @Autowired
    private TerminalMgr terminalMgr; // Asumiendo que tienes algo similar para las terminales

    @FXML
    private javafx.scene.control.TextField numeroDePuertaField;

    @FXML
    private javafx.scene.control.TextField terminalAsociadaField;

    @FXML
    public void handleRegisterPuerta() {
        String numeroDePuerta = numeroDePuertaField.getText().trim();
        String terminalAsociada = terminalAsociadaField.getText().trim();

        // Validaciones básicas
        if (numeroDePuerta.isEmpty()) {
            System.err.println("El número de puerta es obligatorio.");
            return;
        }

        if (terminalAsociada.isEmpty()) {
            System.err.println("La terminal asociada es obligatoria.");
            return;
        }

        // Comprobar si la terminal asociada existe
        Terminal terminal = terminalMgr.findTerminalById(terminalAsociada); // Suponiendo que tienes un método similar en TerminalMgr
        if (terminal == null) {
            System.err.println("La terminal asociada no existe.");
            return;
        }


        // Crear y guardar la nueva puerta
        Puerta nuevaPuerta = new Puerta(numeroDePuerta, terminal);
        puertaMgr.addPuerta(nuevaPuerta);
    }
}
