package uy.edu.um.airport.ui.Terminal;

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
import uy.edu.um.airport.entities.Terminal.Terminal;
import uy.edu.um.airport.entities.Terminal.TerminalMgr;
import uy.edu.um.airport.entities.Usuario.Usuario;
import uy.edu.um.airport.session.Session;

@Component
public class TerminalController {

    @Autowired
    private TerminalMgr terminalMgr;

    @Autowired
    private AeropuertoMgr aeropuertoMgr;


    @FXML
    private TextField txtTerminalNombre;

    @FXML
    private TextField txtCodigoIATAaeropuerto;

    @FXML
    public void handleRegisterTerminal() {
        String nombreTerminal = txtTerminalNombre.getText().trim();

        Usuario usuarioLogueado = Session.getInstance().getCurrentUser();
        Aeropuerto aeropuertoUsuario = usuarioLogueado.getAeropuerto();

        if (nombreTerminal.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error en el Registro de Terminal", "El campo \"Terminal a Asociar\" no puede ser vacío.");
            return;
        }

        if (terminalMgr.findTerminalByNombre(nombreTerminal) != null && terminalMgr.findTerminalByNombre(nombreTerminal).getAeropuerto().equals(aeropuertoUsuario)) {
            showAlert(Alert.AlertType.ERROR, "Error en el Registro de Terminal", "Una terminal con este nombre ya se encuentra asociada en el sistema.");
            return;
        }

        if (!nombreTerminal.matches("^[a-zA-Z]+$")) {
            showAlert(Alert.AlertType.ERROR, "Error en el Registro de Terminal", "El nombre de la terminal solo puede contener letras.");
            return;
        }

        Terminal nuevaTerminal = new Terminal(nombreTerminal, null, aeropuertoUsuario);
        terminalMgr.addTerminal(nuevaTerminal);
        showAlert(Alert.AlertType.CONFIRMATION, "Registro de Terminal Exitoso", "La terminal " + nombreTerminal + " fue asociada al aeropuerto " + aeropuertoUsuario.getNombre());

    }

    @FXML
    public void handleRegisterTerminalSuperUsuario() {
        String nombreTerminal = txtTerminalNombre.getText().trim();
        String codigoIATAaeropuerto = txtCodigoIATAaeropuerto.getText().trim();
        Aeropuerto aeropuerto = aeropuertoMgr.findAeropuertoByCodigoIATA(codigoIATAaeropuerto);

        if (nombreTerminal.isEmpty() || codigoIATAaeropuerto.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error en el Registro de Terminal", "Todos los campos son obligatorios.");
            return;
        }

        if (terminalMgr.findTerminalByNombre(nombreTerminal) != null) {
            showAlert(Alert.AlertType.ERROR, "Error en el Registro de Terminal", "Una terminal con este nombre ya se encuentra asociada en el sistema.");
            return;
        }

        if (!nombreTerminal.matches("^[a-zA-Z]+$")) {
            showAlert(Alert.AlertType.ERROR, "Error en el Registro de Terminal", "El nombre de la terminal solo puede contener letras.");
            return;
        }

        Terminal nuevaTerminal = new Terminal(nombreTerminal, null, aeropuerto);
        terminalMgr.addTerminal(nuevaTerminal);
        showAlert(Alert.AlertType.CONFIRMATION, "Registro de Terminal Exitoso", "La terminal " + nombreTerminal + " fue asociada al aeropuerto " + aeropuerto.getNombre());

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
