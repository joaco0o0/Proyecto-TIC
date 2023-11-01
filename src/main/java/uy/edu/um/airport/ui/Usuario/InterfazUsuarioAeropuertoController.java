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
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import uy.edu.um.airport.entities.Aerolinea.Aerolinea;
import uy.edu.um.airport.entities.Aeropuerto.Aeropuerto;
import uy.edu.um.airport.entities.Aeropuerto.AeropuertoMgr;
import uy.edu.um.airport.entities.Usuario.Usuario;
import uy.edu.um.airport.entities.Usuario.UsuarioMgr;
import uy.edu.um.airport.entities.Vuelo.Vuelo;
import uy.edu.um.airport.entities.Vuelo.VueloMgr;
import uy.edu.um.airport.session.Session;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Component
public class InterfazUsuarioAeropuertoController {
    @Autowired
    private AeropuertoMgr aeropuertoMgr;
    @Autowired
    private VueloMgr vueloMgr;
    @Autowired
    private UsuarioMgr usuarioMgr;

    @Autowired
    private ApplicationContext applicationContext;

    @FXML
    private TableView<Vuelo> tablaVuelos;
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
    private TableColumn<Vuelo,Object> accionColumna;

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
        Usuario usuarioLogueado = Session.getInstance().getCurrentUser();

        numeroVuelo.setCellValueFactory(new PropertyValueFactory<>("numeroVuelo"));
        aerolineaIATA.setCellValueFactory(new PropertyValueFactory<>("aerolineaIATAString"));
        aeropuertoOrigen.setCellValueFactory(new PropertyValueFactory<>("nombreAeropuertoOrigen"));
        aeropuertoDestino.setCellValueFactory(new PropertyValueFactory<>("nombreAeropuertoDestino"));
        ETD.setCellValueFactory(new PropertyValueFactory<>("ETD"));
        ETA.setCellValueFactory(new PropertyValueFactory<>("ETA"));
        estadoVuelo.setCellValueFactory(new PropertyValueFactory<>("estadoVuelo"));

        accionColumna.setCellFactory(param -> new TableCell<>() {
            private final Button btnAceptar = new Button("Aceptar");
            private final Button btnRechazar = new Button("Rechazar");
            private final HBox pane = new HBox(btnAceptar, btnRechazar);
            {
                btnAceptar.setOnAction(event -> aceptarVuelo(getIndex()));
                btnRechazar.setOnAction(event -> rechazarVuelo(getIndex()));
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
        List<Vuelo> listaDeVuelos = aeropuertoMgr.getTodosLosVuelos(usuarioLogueado.getAeropuerto());
        ObservableList<Vuelo> vuelos = FXCollections.observableArrayList(listaDeVuelos);
        tablaVuelos.setItems(vuelos);
    }
        private void rechazarVuelo(int index) {
            Usuario usuarioLogueado = Session.getInstance().getCurrentUser();

            Vuelo vuelo = tablaVuelos.getItems().get(index);
            if (usuarioLogueado.getAeropuerto().equals(vuelo.getAeropuertoOrigen())){
                vuelo.setEstadoAeropuertoOrigen(Vuelo.EstadoVuelo.RECHAZADO);
                vuelo.setEstadoVuelo(Vuelo.EstadoVuelo.RECHAZADO);
                System.out.println("Vuelo rechazado");
                tablaVuelos.refresh();
            } else if (usuarioLogueado.getAeropuerto().equals(vuelo.getAeropuertoDestino())) {
                vuelo.setEstadoAeropuertoDestino(Vuelo.EstadoVuelo.RECHAZADO);
                vuelo.setEstadoVuelo(Vuelo.EstadoVuelo.RECHAZADO);
                System.out.println("Vuelo rechazado");
                tablaVuelos.refresh();
            }
            vueloMgr.updateVuelo(vuelo);
            System.out.println(vuelo.getEstadoVuelo());
            System.out.println(vuelo.getEstadoAeropuertoOrigen());
            System.out.println(vuelo.getEstadoAeropuertoDestino());
        }

        private void aceptarVuelo(int index) {
            Usuario usuarioLogueado = Session.getInstance().getCurrentUser();

            Vuelo vuelo = tablaVuelos.getItems().get(index);
            if (usuarioLogueado.getAeropuerto().equals(vuelo.getAeropuertoOrigen())){
                vuelo.setEstadoAeropuertoOrigen(Vuelo.EstadoVuelo.ACEPTADO);
                System.out.println("Vuele aceptado en Origen");
                if (vuelo.getEstadoAeropuertoDestino().equals(Vuelo.EstadoVuelo.ACEPTADO)){
                    vuelo.setEstadoVuelo(Vuelo.EstadoVuelo.ACEPTADO);
                    System.out.println("Vuele aceptado totalmente");
                }
                tablaVuelos.refresh();
            } else if (usuarioLogueado.getAeropuerto().equals(vuelo.getAeropuertoDestino())) {
                vuelo.setEstadoAeropuertoDestino(Vuelo.EstadoVuelo.ACEPTADO);
                System.out.println("Vuele aceptado EN Destino");
                if (vuelo.getEstadoAeropuertoOrigen().equals(Vuelo.EstadoVuelo.ACEPTADO)){
                    vuelo.setEstadoVuelo(Vuelo.EstadoVuelo.ACEPTADO);
                    System.out.println("Vuele aceptado totalmente");
                }
                tablaVuelos.refresh();
            }
            vueloMgr.updateVuelo(vuelo);
            System.out.println(vuelo.getEstadoVuelo());
            System.out.println(vuelo.getEstadoAeropuertoOrigen());
            System.out.println(vuelo.getEstadoAeropuertoDestino());
        }
}

