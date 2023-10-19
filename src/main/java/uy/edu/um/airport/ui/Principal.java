package uy.edu.um.airport.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import uy.edu.um.airport.entities.Role.Rol;
import uy.edu.um.airport.entities.Usuario.Usuario;
import uy.edu.um.airport.entities.Usuario.UsuarioMgr;

import java.io.IOException;

@Component
public class Principal {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private UsuarioMgr usuarioMgr;

    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtContraseña;

    @FXML
    private Button btnRegistrarse;

    @FXML
    private Button btnListaVuelos;

    @FXML
    public void openRegistroUsuarios() {
        loadWindow("/Templates/Inicio/RegistroDeUsuarios.fxml");
    }

    @FXML
    public void openInterfazUsuarioFinal() {
        loadWindow("/Templates/Inicio/InterfazUsuarioFinal.fxml");
    }

    @FXML
    public void openInterfazStaffAeropuerto() {
        loadWindow("/Templates/Inicio/InterfazStaffAeropuerto.fxml");
    }

    @FXML
    public void openInterfazStaffAerolinea() {
        loadWindow("/Templates/Inicio/InterfazStaffAerolinea.fxml");
    }

    @FXML
    public void openInterfazSuperUsuario() {
        loadWindow("/Templates/Inicio/InterfazSuperUsuario.fxml");
    }

    @FXML
    public void openListaVuelos() {
        loadWindow("/Templates/Inicio/TablaVuelosLista.fxml");
    }

    //Siguiente fxml

    private void loadWindow(String fxmlPath) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
            fxmlLoader.setControllerFactory(applicationContext::getBean);
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleLogin(ActionEvent actionEvent) {
        String email = txtEmail.getText();
        String contraseña = txtContraseña.getText();

        if (email.isEmpty() || contraseña.isEmpty()) {
            System.err.println("Todos los campos son obligatorios.");
            return;
        }

        Usuario user = usuarioMgr.findUsuarioByEmail(email);
        if (user == null) {
            System.err.println("No existe ningún usuario con ese email.");
            return;
        }

        if (!user.getPassword().equals(contraseña)) {
            System.err.println("La contraseña es incorrecta.");
            return;
        }

        System.out.println("Usuario logueado con éxito: " + user.getNombre() + " " + user.getApellido());
        Rol userRole = user.getRol();
        switch (userRole) {
            case SUPER_USUARIO:
                openInterfazSuperUsuario();
                break;

            case USUARIO_FINAL:
                openInterfazUsuarioFinal();
                break;

            case STAFF_AEROPUERTO:
                openInterfazStaffAeropuerto();
                break;

            case STAFF_AEROLINEA:
                openInterfazStaffAerolinea();
                break;

            default:
                System.err.println("Rol de usuario no reconocido.");
                break;

        }
    }
}
