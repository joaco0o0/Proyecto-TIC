package uy.edu.um.airport.ui.Usuario;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import uy.edu.um.airport.entities.Role.Rol;
import uy.edu.um.airport.entities.Usuario.Usuario;
import uy.edu.um.airport.entities.Usuario.UsuarioMgr;
import uy.edu.um.airport.ui.Principal;

import java.io.IOException;
import java.time.LocalDate;

@Component
public class InterfazSuperUsuarioController {

    @Autowired
    private ApplicationContext applicationContext;

    @FXML
    public void openListaVuelos() {
        loadWindow("/Templates/Inicio/TablaVuelosLista.fxml");
    }


    //Servicios Aerolinea
    @FXML
    public void openCrearAerolinea() {loadWindow("/Templates/Inicio/CrearAerolinea.fxml");}


    //Servicios Aeropuerto
    @FXML
    public void openCrearAeropuerto() {loadWindow("/Templates/Inicio/CrearAeropuerto.fxml");}


    //Servicios Usuarios
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




    //Metodos en la interfaz Super Usuario / Admin

    public void crearAerolinea





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
