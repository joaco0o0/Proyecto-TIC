package uy.edu.um.airport.ui.Usuario;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import uy.edu.um.airport.entities.Aeropuerto.Aeropuerto;
import uy.edu.um.airport.entities.Aeropuerto.AeropuertoMgr;
import uy.edu.um.airport.entities.Pasajeros.Pasajeros;
import uy.edu.um.airport.entities.Pasajeros.PasajerosMgr;
import uy.edu.um.airport.entities.Puerta.PuertaMgr;
import uy.edu.um.airport.entities.Usuario.Usuario;
import uy.edu.um.airport.entities.Usuario.UsuarioMgr;
import uy.edu.um.airport.entities.Vuelo.Vuelo;
import uy.edu.um.airport.entities.Vuelo.VueloMgr;
import uy.edu.um.airport.session.Session;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Component
public class InterfazUsuarioFinalController {

    @Autowired
    private AeropuertoMgr aeropuertoMgr;
    @Autowired
    private VueloMgr vueloMgr;
    @Autowired
    private UsuarioMgr usuarioMgr;
    @Autowired
    private PuertaMgr puertaMgr;
    @Autowired
    private PasajerosMgr pasajerosMgr;


    @Autowired
    private ApplicationContext applicationContext;

    @FXML
    public void openListaVuelos() {
        loadWindow("/Templates/Inicio/TablaVuelosLista.fxml");
    }

    @FXML
    public void openComprarPasajeUsuarioFinal() {
        loadWindow("/Templates/Inicio/ComprarPasajeUsuarioFinal.fxml");
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


    public void iracomprarVuelos(ActionEvent actionEvent) {
        loadWindow("/Templates/Inicio/ComprarPasajeUsuarioFinal.fxml");
    }
}
