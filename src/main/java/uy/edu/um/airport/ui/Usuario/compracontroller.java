package uy.edu.um.airport.ui.Usuario;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import uy.edu.um.airport.entities.Aeropuerto.Aeropuerto;
import uy.edu.um.airport.entities.Aeropuerto.AeropuertoMgr;
import uy.edu.um.airport.entities.Pasajeros.Pasajeros;
import uy.edu.um.airport.entities.Pasajeros.PasajerosMgr;
import uy.edu.um.airport.entities.Puerta.Puerta;
import uy.edu.um.airport.entities.Puerta.PuertaMgr;
import uy.edu.um.airport.entities.Usuario.Usuario;
import uy.edu.um.airport.entities.Usuario.UsuarioMgr;
import uy.edu.um.airport.entities.Vuelo.Vuelo;
import uy.edu.um.airport.entities.Vuelo.VueloMgr;
import uy.edu.um.airport.session.Session;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class compracontroller {
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
    private TableView<Vuelo> tablacompras;
    @FXML
    private TableColumn<Vuelo, Long> numeroVuelo;
    @FXML
    private TableColumn<Vuelo, String> aerolineaIATA;
    @FXML
    private TableColumn<Vuelo, Aeropuerto> aeropuertoOrigen;
    @FXML
    private TableColumn<Vuelo, Aeropuerto> aeropuertoDestino;
    @FXML
    private TableColumn<Vuelo, LocalDate> ETD;
    @FXML
    private TableColumn<Vuelo, LocalDate> ETA;
    @FXML
    private TableColumn<Vuelo, Vuelo.EstadoVuelo> estadoVuelo;
    @FXML
    private TableColumn<Vuelo,Object> accion;

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
        numeroVuelo.setCellValueFactory(new PropertyValueFactory<>("numeroVuelo"));
        aerolineaIATA.setCellValueFactory(new PropertyValueFactory<>("aerolineaIATA"));
        aeropuertoOrigen.setCellValueFactory(new PropertyValueFactory<>("aeropuertoOrigen"));
        aeropuertoDestino.setCellValueFactory(new PropertyValueFactory<>("aeropuertoDestino"));
        ETD.setCellValueFactory(new PropertyValueFactory<>("ETD"));
        ETA.setCellValueFactory(new PropertyValueFactory<>("ETA"));
        estadoVuelo.setCellValueFactory(new PropertyValueFactory<>("estadoVuelo"));
        accion.setCellFactory(param -> new TableCell<>() {
            private final Button btnComprar = new Button("Comprar");

            private final HBox pane = new HBox(btnComprar);
            {
                btnComprar.setOnAction(event -> comprarVuelo(getIndex()));
                pane.setSpacing(10);
            }
            @Override
            protected void updateItem(Object item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(pane);
                }
            }
        });

        List<Vuelo> listaDeVuelos = vueloMgr.getVuelosAceptados();
        ObservableList<Vuelo> vuelos = FXCollections.observableArrayList(listaDeVuelos);
        tablacompras.setItems(vuelos);
    }

    private void comprarVuelo(int index) {
        Usuario usuarioLogueado = Session.getInstance().getCurrentUser();
        Vuelo vuelo = tablacompras.getItems().get(index);

        Pasajeros pasajeros = new Pasajeros(vuelo, usuarioLogueado);
        pasajerosMgr.addPasajero(pasajeros);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Compra");
        alert.setHeaderText("Compra realizada");
        alert.setContentText("El pasaje ha sido comprado con éxito.");
        System.out.println("pasaje comprado.");
    }
}
