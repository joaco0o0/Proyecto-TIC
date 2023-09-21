package uy.edu.um.airport.ui.Usuario;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uy.edu.um.airport.entities.Usuario.Usuario;
import uy.edu.um.airport.entities.Usuario.UsuarioMgr;
import uy.edu.um.airport.persistence.UsuarioRepository;

import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

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
    private RadioButton radioButtonPromociones;

    public void handleRegister(javafx.event.ActionEvent actionEvent) {
        String nombre = txtNombre.getText();
        String apellidos = txtApellidos.getText();
        String email = txtEmail.getText();
        String confirmarEmail = txtConfirmarEmail.getText();
        String contraseña = txtContraseña.getText();
        String confirmarContraseña = txtConfirmarContraseña.getText();
        String pasaporte = txtPasaporte.getText();
        LocalDate fechaNacimiento = datePicker.getValue();
        boolean aceptaTerminos = radioButtonTerminos.isSelected();
        boolean recibePromociones = radioButtonPromociones.isSelected();

        if (nombre.isEmpty() || apellidos.isEmpty() || email.isEmpty() || confirmarEmail.isEmpty() || contraseña.isEmpty() || confirmarContraseña.isEmpty() || pasaporte.isEmpty() || fechaNacimiento == null) {
            System.err.println("Todos los campos son obligatorios excepto el de promociones.");
            return;
        }

        if (!email.equals(confirmarEmail)) {
            System.err.println("Los emails no coinciden.");
            return;
        }

        if (!contraseña.equals(confirmarContraseña)) {
            System.err.println("Las contraseñas no coinciden.");
            return;
        }

        if (!aceptaTerminos) {
            System.err.println("Debe aceptar los términos y condiciones.");
            return;
        }

        Usuario newUser = new Usuario(nombre,apellidos,fechaNacimiento,pasaporte,email,contraseña);
        usuarioMgr.addUsuario(newUser);
        // Aquí debes incluir el código que procesará los datos del formulario
        System.out.println("Usuario registrado con éxito: " + nombre + " " + apellidos);
    }


}

