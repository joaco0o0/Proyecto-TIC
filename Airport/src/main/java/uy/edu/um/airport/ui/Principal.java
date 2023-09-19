package uy.edu.um.airport.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;
import uy.edu.um.airport.ui.Usuario.UsuarioController;

import java.io.IOException;

@Component
public class Principal {
    @FXML
    private Button btnRegistrarse;

    @FXML
    private Button btnListaVuelos;

    @FXML
    public void openRegistroUsuarios(ActionEvent event) {
        try {
            Parent registroUsuariosRoot = FXMLLoader.load(getClass().getResource("/templates/inicio/RegistroDeUsuarios.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(registroUsuariosRoot));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void openListaVuelos(ActionEvent event) {
        try {
            Parent listaVuelosRoot = FXMLLoader.load(getClass().getResource("/templates/inicio/TablaVuelosLista.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(listaVuelosRoot));
            stage.setTitle("Lista de Vuelos");  // Puedes establecer un título para esta ventana si lo deseas
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Puedes agregar aquí mensajes adicionales para manejar errores si es necesario.
        }
    }
}
