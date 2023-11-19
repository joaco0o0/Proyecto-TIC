package uy.edu.um.airport.ui.Usuario;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uy.edu.um.airport.entities.Aerolinea.AerolineaMgr;
import uy.edu.um.airport.entities.Role.Rol;
import uy.edu.um.airport.entities.Usuario.Usuario;
import uy.edu.um.airport.entities.Usuario.UsuarioMgr;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.time.LocalDate;

@Component
public class UsuarioController {

    @Autowired
    private UsuarioMgr usuarioMgr;

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellidos;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtConfirmarEmail;
    @FXML
    private TextField txtContraseña;
    @FXML
    private TextField txtConfirmarContraseña;
    @FXML
    private TextField txtPasaporte;
    @FXML
    private DatePicker datePicker;
    @FXML
    private RadioButton radioButtonTerminos;

    @FXML
    public void handleRegister() {
        String nombre = txtNombre.getText();
        String apellidos = txtApellidos.getText();
        String email = txtEmail.getText();
        String confirmarEmail = txtConfirmarEmail.getText();
        String contraseña = txtContraseña.getText();
        String confirmarContraseña = txtConfirmarContraseña.getText();
        String pasaporte = txtPasaporte.getText();
        LocalDate fechaNacimiento = datePicker.getValue();
        Rol rol = Rol.USUARIO_FINAL;
        boolean aceptaTerminos = radioButtonTerminos.isSelected();

        if (nombre.isEmpty() || apellidos.isEmpty() || email.isEmpty() || confirmarEmail.isEmpty() || contraseña.isEmpty() || confirmarContraseña.isEmpty() || pasaporte.isEmpty() || fechaNacimiento == null) {
            showAlert(Alert.AlertType.ERROR, "Error en el Registro de Usuario", "Todos los campos son obligatorios.");
            return;
        }

        if (usuarioMgr.findUsuarioByPasaporte(pasaporte) != null) {
            showAlert(Alert.AlertType.ERROR, "Error en el Registro de Usuario", "El pasaporte ingresado ya se encuentra registrado en el sistema.");
            return;
        }

        if (usuarioMgr.findUsuarioByEmail(email) != null) {
            showAlert(Alert.AlertType.ERROR, "Error en el Registro de Usuario", "El email ingresado ya se encuentra registrado en el sistema.");
            return;
        }

        if (!email.equals(confirmarEmail)) {
            showAlert(Alert.AlertType.ERROR, "Error en el Registro de Usuario", "Los campos email deben coincidir.");
            return;
        }

        if (!contraseña.equals(confirmarContraseña)) {
            showAlert(Alert.AlertType.ERROR, "Error en el Registro de Usuario", "Los campos contraseña deben coincidir.");
            return;
        }

        if (!aceptaTerminos) {
            showAlert(Alert.AlertType.ERROR, "Error en el Registro de Usuario", "Debe aceptar los términos y condiciones para registrarse.");
            return;
        }

        LocalDate hoy = LocalDate.now();
        LocalDate hace18Años = hoy.minusYears(18);
        if (fechaNacimiento.isAfter(hace18Años)) {
            showAlert(Alert.AlertType.ERROR, "Error en el Registro de Usuario", "Debe ser mayor de edad para registrarse.");
            return;
        }

        usuarioMgr.addUsuario(new Usuario(nombre, apellidos, fechaNacimiento, pasaporte, email, contraseña, rol, null, null));
        showAlert(Alert.AlertType.CONFIRMATION, "Registro de Usuario Exitoso", "Usuario registrado con éxito: " + nombre + " " + apellidos);

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
