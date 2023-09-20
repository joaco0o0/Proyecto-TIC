package uy.edu.um.airport.ui.Usuario;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uy.edu.um.airport.entities.Usuario.Usuario;
import uy.edu.um.airport.entities.Usuario.UsuarioMgr;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Component
public class UsuarioController {

    @Autowired
    private UsuarioMgr usuarioMgr;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtFechaNacimiento;

    @FXML
    private TextField txtPasaporte;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPassword;

    @FXML
    public void handleRegister() {
        try {
            String nombre = txtNombre.getText();
            String apellido = txtApellido.getText();
            String fechaNacimientoStr = txtFechaNacimiento.getText();

            // Imprimir los valores para diagnóstico
            System.out.println("Nombre: " + nombre);
            System.out.println("Apellido: " + apellido);
            System.out.println("Fecha Nacimiento: " + fechaNacimientoStr);
            System.out.println("Pasaporte: " + txtPasaporte.getText());
            System.out.println("Email: " + txtEmail.getText());
            System.out.println("Contraseña: " + txtPassword.getText());

            if (nombre.isEmpty() || apellido.isEmpty() || fechaNacimientoStr.isEmpty() || txtPasaporte.getText().isEmpty() || txtEmail.getText().isEmpty() || txtPassword.getText().isEmpty()) {
                System.out.println("Todos los campos deben ser llenados.");
                return;
            }

            LocalDate fechaNacimiento = LocalDate.parse(fechaNacimientoStr); // Asegúrate de manejar cualquier posible excepción aquí
            String pasaporte = txtPasaporte.getText();
            String email = txtEmail.getText();
            String password = txtPassword.getText();

            Usuario newUser = new Usuario(nombre, apellido, fechaNacimiento, pasaporte, email, password);
            usuarioMgr.addUsuario(newUser);

            // Mostrar mensaje de éxito
            System.out.println("Usuario registrado exitosamente.");

        } catch (DateTimeParseException e) {
            System.out.println("La fecha ingresada no es válida.");
        }
    }
}

