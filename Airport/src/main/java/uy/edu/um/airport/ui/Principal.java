package uy.edu.um.airport.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Principal {

    @Autowired
    private ApplicationContext applicationContext;

    @FXML
    private Button btnRegistrarse;

    @FXML
    private Button btnListaVuelos;

    @FXML
    public void openRegistroUsuarios(ActionEvent event) {
        loadWindow("/templates/inicio/RegistroDeUsuarios.fxml");
    }

    @FXML
    public void openListaVuelos(ActionEvent event) {
        loadWindow("/templates/inicio/TablaVuelosLista.fxml");
    }

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
}
