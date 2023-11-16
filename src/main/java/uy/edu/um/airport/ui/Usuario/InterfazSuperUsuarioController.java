package uy.edu.um.airport.ui.Usuario;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import uy.edu.um.airport.entities.Aerolinea.AerolineaMgr;
import uy.edu.um.airport.entities.Usuario.UsuarioMgr;

import java.io.IOException;

@Component
public class InterfazSuperUsuarioController {
    @Autowired
    private AerolineaMgr aerolineaMgr;

    @Autowired
    private UsuarioMgr usuarioMgr;

    @Autowired
    private ApplicationContext applicationContext;

    @FXML
    public void openListaVuelos() {
        loadWindow("/Templates/Inicio/TablaVuelosLista.fxml");
    }


    @FXML
    public void openCrearVueloSuperUsuario() {
        loadWindow("/Templates/Inicio/CrearVueloSuperUsuario.fxml");
    }

    @FXML
    public void openAsignarPasajeroAVuelo() {
        loadWindow("/Templates/Inicio/AsignarPasajeroAVuelo.fxml");
    }


    @FXML
    public void openSuperUsuarioCrearAvion() {
        loadWindow("/Templates/Inicio/SuperUsuarioCrearAvion.fxml");
    }


    @FXML
    public void openSuperUsaurioAsociarAerolineaAAeropuerto() {
        loadWindow("/Templates/Inicio/SuperUsuarioAsociarAerolineaAAeropuerto.fxml");
    }


    @FXML
    public void openRegistroAerolinea() {loadWindow("/Templates/Inicio/CrearAerolinea.fxml");}


    @FXML
    public void openCrearAeropuerto() {loadWindow("/Templates/Inicio/CrearAeropuerto.fxml");}


    @FXML
    public void openRegistroUsuarios() {
        loadWindow("/Templates/Inicio/RegistroDeUsuarios.fxml");
    }

    @FXML
    public void openRegistroPersonalAerolinea() {
        loadWindow("/Templates/Inicio/RegistroPersonalAerolinea.fxml");
    }

    @FXML
    public void openRegistroPersonalAeropuerto() {
        loadWindow("/Templates/Inicio/RegistroPersonalAeropuerto.fxml");
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
