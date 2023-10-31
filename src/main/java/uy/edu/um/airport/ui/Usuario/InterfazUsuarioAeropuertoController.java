package uy.edu.um.airport.ui.Usuario;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import uy.edu.um.airport.entities.Aerolinea.AerolineaMgr;
import uy.edu.um.airport.entities.Aeropuerto.Aeropuerto;
import uy.edu.um.airport.entities.Aeropuerto.AeropuertoMgr;
import uy.edu.um.airport.entities.Role.Rol;
import uy.edu.um.airport.entities.Usuario.Usuario;
import uy.edu.um.airport.entities.Usuario.UsuarioMgr;
import uy.edu.um.airport.entities.Vuelo.Vuelo;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Component
public class InterfazUsuarioAeropuertoController {
    @Autowired
    private AeropuertoMgr aeropuertoMgr;

    @Autowired
    private UsuarioMgr usuarioMgr;

    @Autowired
    private ApplicationContext applicationContext;

    @FXML
    private TableView<Vuelo> tablaVuelos;
    @FXML
    private TableColumn<Vuelo, Long> numVuelo;
    @FXML
    private TableColumn<Vuelo, String> Origen;
    @FXML
    private TableColumn<Vuelo, String> Destino;
    @FXML
    private TableColumn<Vuelo, LocalDate> Etd;
    @FXML
    private TableColumn<Vuelo, LocalDate> Eta;
    @FXML
    private TableColumn<Vuelo, Vuelo.EstadoVuelo> Estado;

    @FXML
    public void openListaVuelos() {
        loadWindow("/Templates/Inicio/TablaVuelosLista.fxml");
    }

    @FXML
    public void openAsociarAerolineaAAeropuerto() {
        loadWindow("/Templates/Inicio/AsociarAerolineaAAeropuerto.fxml");
    }

    @FXML
    public void openCrearPuerta(){
        loadWindow("/Templates/Inicio/CrearPuerta.fxml");
    }

    @FXML
    public void openCrearTerminal(){
        loadWindow("/Templates/Inicio/CrearTerminal.fxml");
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

    public void initialize() {
        // Configurar CellValueFactories para las columnas
        numVuelo.setCellValueFactory(new PropertyValueFactory<>("numeroVuelo"));
        Origen.setCellValueFactory(new PropertyValueFactory<>("origen"));
        Destino.setCellValueFactory(new PropertyValueFactory<>("destino"));
        Etd.setCellValueFactory(new PropertyValueFactory<>("etd"));
        Eta.setCellValueFactory(new PropertyValueFactory<>("eta"));
        Estado.setCellValueFactory(new PropertyValueFactory<>("estado"));

        // Obtener los datos y configurarlos en la tabla
        List<Vuelo> listaDeVuelos = aeropuertoMgr.getTodosLosVuelos();
        ObservableList<Vuelo> vuelos = FXCollections.observableArrayList(listaDeVuelos);
        tablaVuelos.setItems(vuelos);
    }

}

