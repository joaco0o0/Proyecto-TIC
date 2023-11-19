package uy.edu.um.airport.ui.Aerolinea;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uy.edu.um.airport.entities.Aerolinea.Aerolinea;
import uy.edu.um.airport.entities.Aerolinea.AerolineaMgr;
import uy.edu.um.airport.entities.Role.Rol;
import uy.edu.um.airport.entities.Usuario.Usuario;
import uy.edu.um.airport.entities.Usuario.UsuarioMgr;
//import uy.edu.um.airport.entities.Aerolinea.AerolineaService;

import java.time.LocalDate;

@Component
public class AerolineaController {

    @Autowired
    private AerolineaMgr aerolineaMgr;
    @Autowired
    private UsuarioMgr usuarioMgr;

    @FXML
    private TextField txtIATAaerolinea;
    @FXML
    private TextField txtICAO;
    @FXML
    private TextField txtNombreAerolinea;
    @FXML
    private TextField txtPais;

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
    private TextField txtpasaporte;
    @FXML
    private DatePicker datePicker;





    @FXML
    private void handleAerolinearegister(){
        String codigoIATA = txtIATAaerolinea.getText();
        String codigoICAO = txtICAO.getText();
        String nombreAerolinea = txtNombreAerolinea.getText();
        String pais = txtPais.getText();


        if(nombreAerolinea.isEmpty() || codigoIATA.isEmpty() || codigoICAO.isEmpty() || pais.isEmpty()){
            showAlert(Alert.AlertType.ERROR, "Error en el Registro de Aerolínea", "Todos los campos son obligatorios.");
            return;
        }

        if (aerolineaMgr.existsByNombre(nombreAerolinea)){
            showAlert(Alert.AlertType.ERROR, "Error en el Registro de Aerolínea", "Ya existe una aerolínea registrada en el sistema con este nombre.");
            return;
        }

        if (aerolineaMgr.existsByCodigoIATA(codigoIATA)) {
            showAlert(Alert.AlertType.ERROR, "Error en el Registro de Aerolínea", "Ya existe una aerolínea registrada en el sistema con este código IATA.");
            return;
        }

        if (aerolineaMgr.existsByCodigoICAO(codigoICAO)) {
            showAlert(Alert.AlertType.ERROR, "Error en el Registro de Aerolínea", "Ya existe una aerolínea registrada en el sistema con este código ICAO.");
            return;
        }

        Aerolinea aerolinea = new Aerolinea(codigoIATA, codigoICAO, nombreAerolinea, pais);
        aerolineaMgr.addAerolinea(aerolinea);
        showAlert(Alert.AlertType.CONFIRMATION, "Registro de Aerolínea Exitoso", "Aerolínea registrada con el nombre " + nombreAerolinea + " " + " y código IATA " + codigoIATA);
    }

    @FXML
    private void handleUsuarioAerolineaRegister(){
        String nombre = txtNombre.getText();
        String apellidos = txtApellidos.getText();
        String email = txtEmail.getText();
        String confirmarContraseña = txtConfirmarContraseña.getText();
        String contraseña = txtContraseña.getText();
        String confirmarEmail = txtConfirmarEmail.getText();
        LocalDate fechaNacimiento = datePicker.getValue();
        String codigoIATA = txtIATAaerolinea.getText();
        Aerolinea aerolineaEncontrada = aerolineaMgr.findAerolineaByCodigoIATA(codigoIATA);
        String pasaporte = txtpasaporte.getText();
        Rol rol = Rol.STAFF_AEROLINEA;


        if (nombre.isEmpty() || apellidos.isEmpty() || email.isEmpty() || confirmarEmail.isEmpty() || contraseña.isEmpty() || confirmarContraseña.isEmpty() || pasaporte.isEmpty() || fechaNacimiento == null || codigoIATA.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error en el Registro de Personal de Aerolínea", "Todos los campos son obligatorios.");
            return;
        }

        LocalDate hoy = LocalDate.now();
        LocalDate hace18Años = hoy.minusYears(18);
        if (fechaNacimiento.isAfter(hace18Años)) {
            showAlert(Alert.AlertType.ERROR, "Error en el Registro de Personal de Aerolínea", "Debe ser mayor de edad para registrarse.");
            return;
        }

        if (usuarioMgr.findUsuarioByPasaporte(pasaporte) != null) {
            showAlert(Alert.AlertType.ERROR, "Error en el Registro de Personal de Aerolínea", "El pasaporte ingresado ya se encuentra registrado en el sistema.");
            return;
        }

        if (usuarioMgr.findUsuarioByEmail(email) != null) {
            showAlert(Alert.AlertType.ERROR, "Error en el Registro de Personal de Aerolínea", "El email ingresado ya se encuentra registrado en el sistema.");
            return;
        }


        if (!email.equals(confirmarEmail)) {
            showAlert(Alert.AlertType.ERROR, "Error en el Registro de Personal de Aerolínea", "Los campos email deben coincidir.");
            return;
        }

        if (!contraseña.equals(confirmarContraseña)) {
            showAlert(Alert.AlertType.ERROR, "Error en el Registro de Personal de Aerolínea", "Los campos contraseña deben coincidir.");
            return;
        }

        if (aerolineaEncontrada == null) {
            showAlert(Alert.AlertType.ERROR, "Error en el Registro de Personal de Aerolínea", "La aerolínea solicitada no se encuentra registrada en el sistema.");
            return;
        }

        // Crear el nuevo usuario con la aerolínea encontrada
        usuarioMgr.addUsuario(new Usuario(nombre, apellidos, fechaNacimiento, pasaporte, email, contraseña, rol, aerolineaEncontrada, null));
        showAlert(Alert.AlertType.CONFIRMATION, "Registro de Usuario Personal de Aerolínea Exitoso", "Usuario registrado con éxito: " + nombre + " " + apellidos + " para la aerolínea " + aerolineaEncontrada.getNombre());

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
