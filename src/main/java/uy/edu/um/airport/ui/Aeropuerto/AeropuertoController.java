package uy.edu.um.airport.ui.Aeropuerto;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uy.edu.um.airport.entities.Aeropuerto.Aeropuerto;
import uy.edu.um.airport.entities.Aeropuerto.AeropuertoMgr;
import uy.edu.um.airport.entities.Role.Rol;
import uy.edu.um.airport.entities.Usuario.Usuario;
import uy.edu.um.airport.entities.Usuario.UsuarioMgr;

import java.time.LocalDate;

@Component
public class AeropuertoController {

    @Autowired
    private AeropuertoMgr aeropuertoMgr;
    @Autowired
    private UsuarioMgr usuarioMgr;

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtIATA;
    @FXML
    private TextField txtCiudad;
    @FXML
    private TextField txtPais;

    @FXML
    private TextField txtNombreUser;
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
    private void handleAeropuertoRegister() {
        String nombre = txtNombre.getText();
        String codigoIATA = txtIATA.getText();
        String ciudad = txtCiudad.getText();
        String pais = txtPais.getText();

        try {

            if(nombre.isEmpty() || codigoIATA.isEmpty() || ciudad.isEmpty() || pais.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Error en el Registro de Aeropuerto", "Todos los campos son obligatorios.");
                return;
            }

            if (aeropuertoMgr.existsByNombre(nombre)) {
                showAlert(Alert.AlertType.ERROR, "Error en el Registro de Aeropuerto", "Ya existe un aeropuerto registrado en el sistema con este nombre.");
                return;
            }

            if (aeropuertoMgr.existsByCodigoIATA(codigoIATA)) {
                showAlert(Alert.AlertType.ERROR, "Error en el Registro de Aeropuerto", "Ya existe un aeropuerto registrado en el sistema con este código IATA.");
                return;
            }

            // Si no hay conflictos, crear el nuevo aeropuerto
            Aeropuerto aeropuerto = new Aeropuerto(codigoIATA, nombre, ciudad, pais);
            aeropuertoMgr.addAeropuerto(aeropuerto);
            showAlert(Alert.AlertType.CONFIRMATION, "Registro de Aeropuerto Exitoso", "Aeropuerto registrado con el nombre " + nombre + " " + " y código IATA " + codigoIATA + " en " + pais + ", " + ciudad);



        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void handleUsuarioAeropuertoRegister(){
        String nombre = txtNombreUser.getText();
        String apellidos = txtApellidos.getText();
        String email = txtEmail.getText();
        String confirmarEmail = txtConfirmarEmail.getText();
        String contraseña = txtContraseña.getText();
        String confirmarContraseña = txtConfirmarContraseña.getText();
        LocalDate fechaNacimiento = datePicker.getValue();
        String codigoIATAAeropuerto = txtNombre.getText();
        Aeropuerto aeropuerto = aeropuertoMgr.findAeropuertoByCodigoIATA(codigoIATAAeropuerto);
        String pasaporte = txtPasaporte.getText();
        Rol rol = Rol.STAFF_AEROPUERTO;

        if (nombre.isEmpty() || apellidos.isEmpty() || email.isEmpty() || confirmarEmail.isEmpty() || contraseña.isEmpty() || confirmarContraseña.isEmpty() || pasaporte.isEmpty() || fechaNacimiento == null || codigoIATAAeropuerto.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error en el Registro de Personal de Aeropuerto", "Todos los campos son obligatorios.");
            return;
        }

        LocalDate hoy = LocalDate.now();
        LocalDate hace18Años = hoy.minusYears(18);
        if (fechaNacimiento.isAfter(hace18Años)) {
            showAlert(Alert.AlertType.ERROR, "Error en el Registro de Personal de Aeropuerto", "Debe ser mayor de edad para registrarse.");
            return;
        }

        if (usuarioMgr.findUsuarioByPasaporte(pasaporte) != null) {
            showAlert(Alert.AlertType.ERROR, "Error en el Registro de Personal de Aeropuerto", "El pasaporte ingresado ya se encuentra registrado en el sistema.");
            return;
        }

        if (usuarioMgr.findUsuarioByEmail(email) != null) {
            showAlert(Alert.AlertType.ERROR, "Error en el Registro de Personal de Aeropuerto", "El email ingresado ya se encuentra registrado en el sistema.");
            return;
        }


        if (!email.equals(confirmarEmail)) {
            showAlert(Alert.AlertType.ERROR, "Error en el Registro de Personal de Aeropuerto", "Los campos email deben coincidir.");
            return;
        }

        if (!contraseña.equals(confirmarContraseña)) {
            showAlert(Alert.AlertType.ERROR, "Error en el Registro de Personal de Aeropuerto", "Los campos contraseña deben coincidir.");
            return;
        }

        if (aeropuerto == null) {
            showAlert(Alert.AlertType.ERROR, "Error en el Registro de Personal de Aeropuerto", "El aeropuerto solicitado no se encuentra registrado en el sistema.");
            return;
        }

        // Crear el nuevo usuario con el aeropuerto encontrado
        usuarioMgr.addUsuario(new Usuario(nombre, apellidos, fechaNacimiento, pasaporte, email, contraseña, rol, null, aeropuerto));
        showAlert(Alert.AlertType.CONFIRMATION, "Registro de Usuario Personal de Aeropuerto Exitoso", "Usuario registrado con éxito: " + nombre + " " + apellidos + " para el aeropuerto " + aeropuerto.getNombre());

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
