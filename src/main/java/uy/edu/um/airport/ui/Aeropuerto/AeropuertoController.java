package uy.edu.um.airport.ui.Aeropuerto;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
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
    private void handleAeropuertoRegister(){
        String nombre = txtNombre.getText();
        String iata = txtIATA.getText();
        String ciudad = txtCiudad.getText();
        String pais = txtPais.getText();

        Aeropuerto aeropuerto = new Aeropuerto(nombre, iata, ciudad, pais, true);
        aeropuertoMgr.addAeropuerto(aeropuerto);
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
        String nombreAeropuerto = txtNombre.getText();
        String pasaporte = txtPasaporte.getText();
        Rol rol = Rol.STAFF_AEROPUERTO;

        if (!email.equals(confirmarEmail)) {
            System.err.println("Los emails no coinciden.");
            return;
        }

        if (!contraseña.equals(confirmarContraseña)) {
            System.err.println("Las contraseñas no coinciden.");
            return;
        }

        usuarioMgr.addUsuario(new Usuario(nombre, apellidos, fechaNacimiento, pasaporte, email, contraseña, rol, nombreAeropuerto));
    }
}
