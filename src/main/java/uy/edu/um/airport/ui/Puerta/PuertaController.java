package uy.edu.um.airport.ui.Puerta;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uy.edu.um.airport.entities.Aeropuerto.Aeropuerto;
import uy.edu.um.airport.entities.Aeropuerto.AeropuertoMgr;
import uy.edu.um.airport.entities.Puerta.Puerta;
import uy.edu.um.airport.entities.Puerta.PuertaMgr;
import uy.edu.um.airport.entities.Terminal.Terminal;
import uy.edu.um.airport.entities.Terminal.TerminalMgr;
import uy.edu.um.airport.entities.Usuario.Usuario;
import uy.edu.um.airport.session.Session;

@Component
public class PuertaController {

    @Autowired
    private PuertaMgr puertaMgr;

    @Autowired
    private TerminalMgr terminalMgr;

    @Autowired
    private AeropuertoMgr aeropuertoMgr;

    @FXML
    private javafx.scene.control.TextField numeroDePuertaField;

    @FXML
    private javafx.scene.control.TextField terminalAsociadaField;
    @FXML
    private javafx.scene.control.TextField codigoIATAaeropuerto;

    @FXML
    public void handleRegisterPuerta() {
        Usuario usuariologueado = Session.getInstance().getCurrentUser();
        String numeroDePuerta = numeroDePuertaField.getText().trim();
        String terminalAsociada = usuariologueado.getAeropuerto().getCodigoIATA() + terminalAsociadaField.getText().trim();
        Terminal terminal = terminalMgr.findTerminalByNombre(terminalAsociada);

        if (numeroDePuerta.isEmpty() || terminalAsociada.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error en el Registro de Puerta", "Todos los campos son obligatorios.");
            return;
        }

        if (terminalMgr.findTerminalByNombre(terminalAsociada) == null) {
            showAlert(Alert.AlertType.ERROR, "Error en el Registro de Puerta", "La terminal a la que desea registrar la puerta no existe en el sistema.");
            return;
        }

        Puerta nuevaPuerta = new Puerta(numeroDePuerta, terminal);
        puertaMgr.addPuerta(nuevaPuerta);
        showAlert(Alert.AlertType.CONFIRMATION, "Registro de Puerta Exitoso", "La puerta " + numeroDePuerta + " fue asociada a la terminal " + terminal.getNombre());

    }

    @FXML
    public void handleRegisterPuertaSuperUsuario() {
        String numeroDePuerta = numeroDePuertaField.getText().trim();
        String terminalAsociada = terminalAsociadaField.getText().trim();
        Terminal terminal = terminalMgr.findTerminalByNombre(terminalAsociada);
        String codigoIataAeropuerto = codigoIATAaeropuerto.getText().trim();
        Aeropuerto aeropuerto = aeropuertoMgr.findAeropuertoByCodigoIATA(codigoIataAeropuerto);

        if (numeroDePuerta.isEmpty() || terminalAsociada.isEmpty() || codigoIataAeropuerto.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error en el Registro de Puerta", "Todos los campos son obligatorios.");
            return;
        }

        if (terminalMgr.findTerminalByNombre(terminalAsociada) == null) {
            showAlert(Alert.AlertType.ERROR, "Error en el Registro de Puerta", "La terminal a la que desea registrar la puerta no existe en el sistema.");
            return;
        }

        Puerta nuevaPuerta = new Puerta(numeroDePuerta, terminal);
        puertaMgr.addPuerta(nuevaPuerta);
        showAlert(Alert.AlertType.CONFIRMATION, "Registro de Puerta Exitoso", "La puerta " + numeroDePuerta + " fue asociada a la terminal " + terminal.getNombre());

    }



    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);

        ImageView imageView;
        if (alertType == Alert.AlertType.CONFIRMATION) {
            alert.getButtonTypes().setAll(ButtonType.OK);
            imageView = new ImageView(new Image(getClass().getResourceAsStream("/photos/ok.png")));
        } else if (alertType == Alert.AlertType.ERROR) {
            imageView = new ImageView(new Image(getClass().getResourceAsStream("/photos/error.png")));
        } else {
            imageView = null;
        }

        if (imageView != null) {
            imageView.setFitHeight(48);
            imageView.setFitWidth(48);
            alert.setGraphic(imageView);
        }

        alert.showAndWait();
    }

}
