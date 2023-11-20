package uy.edu.um.airport.ui.Usuario;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import uy.edu.um.airport.entities.Aeropuerto.Aeropuerto;
import uy.edu.um.airport.entities.Aeropuerto.AeropuertoMgr;
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
public class InterfazUsuarioAeropuertoController {
    @Autowired
    private AeropuertoMgr aeropuertoMgr;
    @Autowired
    private VueloMgr vueloMgr;
    @Autowired
    private UsuarioMgr usuarioMgr;
    @Autowired
    private PuertaMgr puertaMgr;

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
    private TableColumn<Vuelo, Object> asignarpuertaColumna;
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

        asignarpuertaColumna.setCellFactory(param -> new TableCell<>() {
            private final ComboBox<String> comboBoxPuertas = new ComboBox<>();
            private final HBox pane = new HBox(comboBoxPuertas);

            {
                List<Puerta> puertas = aeropuertoMgr.obtenerPuertas(usuarioLogueado.getAeropuerto());
                comboBoxPuertas.getItems().addAll(puertas.stream().map(Puerta::toString).collect(Collectors.toList()));

                comboBoxPuertas.setOnAction(event -> asignarPuerta(getIndex(), comboBoxPuertas.getValue()));
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

    private void asignarPuerta(int index, String value) {
        Usuario usuarioLogueado = Session.getInstance().getCurrentUser();
        Puerta puerta = puertaMgr.findPuertabyId(value);

        Vuelo vuelo = tablaVuelos.getItems().get(index);
        if (usuarioLogueado.getAeropuerto().equals(vuelo.getAeropuertoOrigen())){
            if(esPuertaDisponibleparaDespegue(value, vuelo.getHorarioDespegue(), usuarioLogueado.getAeropuerto())){
                vuelo.setPuertaOrigen(puerta);
                tablaVuelos.refresh();
                showAlert(Alert.AlertType.CONFIRMATION, "Asignación de Puerta Origen Exitoso", "La puerta deseada fue asignada correctamente.");
            } else {
                showAlert(Alert.AlertType.ERROR, "Error al asignar Puerta Origen", "La puerta que desea asignar ya se encuentra ocupada en el horario seleccionado.");
            }
        } else if (usuarioLogueado.getAeropuerto().equals(vuelo.getAeropuertoDestino())) {
            if (esPuertaDisponibleparaAterrizaje(value, vuelo.getHorarioAterrizaje(), usuarioLogueado.getAeropuerto())) {
                vuelo.setPuertaDestino(puerta);
                tablaVuelos.refresh();
                showAlert(Alert.AlertType.CONFIRMATION, "Asignación de Puerta Destino Exitoso", "La puerta deseada fue asignada correctamente.");
            } else {
                showAlert(Alert.AlertType.ERROR, "Error al asignar Puerta Destino", "La puerta que desea asignar ya se encuentra ocupada en el horario seleccionado.");
            }
        }
        vueloMgr.updateVuelo(vuelo);
    }

    private boolean esPuertaDisponibleparaDespegue(String value, LocalDateTime horarioDespegue, Aeropuerto aeropuerto) {
        List<Vuelo> vuelos = aeropuertoMgr.getTodosLosVuelos(aeropuerto);
        Puerta puerta = puertaMgr.findPuertabyId(value);

        LocalDateTime inicioOcupacionDespegue = horarioDespegue.minusHours(1);
        LocalDateTime finOcupacionDespegue = horarioDespegue.plusMinutes(20);

        for (Vuelo vuelo : vuelos) {
            if (puerta.equals(vuelo.getpuertaOrigen())) {
                LocalDateTime inicioOcupacionExistente = vuelo.getHorarioDespegue().minusHours(1);
                LocalDateTime finOcupacionExistente = vuelo.getHorarioDespegue().plusMinutes(20);

                if (inicioOcupacionDespegue.isBefore(finOcupacionExistente) && finOcupacionDespegue.isAfter(inicioOcupacionExistente)) {
                    return false;
                }
            }
        }
        return true;
    }


    private boolean esPuertaDisponibleparaAterrizaje(String value, LocalDateTime horarioAterrizaje, Aeropuerto aeropuerto) {
        List<Vuelo> vuelos = aeropuertoMgr.getTodosLosVuelos(aeropuerto);
        Puerta puerta = puertaMgr.findPuertabyId(value);
        LocalDateTime finOcupacionAterrizaje = horarioAterrizaje.plusMinutes(60);
        LocalDateTime inicioOcupacionAterrizaje = horarioAterrizaje.minusMinutes(30);

        for (Vuelo vuelo : vuelos) {
            if (puerta.equals(vuelo.getpuertaOrigen()) || puerta.equals(vuelo.getpuertaDestino())) {
                LocalDateTime inicioOcupacionExistente = vuelo.getHorarioAterrizaje().minusMinutes(30);
                LocalDateTime finOcupacionExistente = vuelo.getHorarioAterrizaje().plusMinutes(60);

                if (inicioOcupacionAterrizaje.isBefore(finOcupacionExistente) && finOcupacionAterrizaje.isAfter(inicioOcupacionExistente)) {
                    return false;
                }
            }
        }
        return true;
    }


    private void rechazarVuelo(int index) {
        Usuario usuarioLogueado = Session.getInstance().getCurrentUser();

        Vuelo vuelo = tablaVuelos.getItems().get(index);
        if (usuarioLogueado.getAeropuerto().equals(vuelo.getAeropuertoOrigen())){
            vuelo.setEstadoAeropuertoOrigen(Vuelo.EstadoVuelo.RECHAZADO);
            vuelo.setEstadoVuelo(Vuelo.EstadoVuelo.RECHAZADO);
            vuelo.setPuertaOrigen(null);
            vuelo.setPuertaDestino(null);
            showAlert(Alert.AlertType.CONFIRMATION, "Vuelo Rechazado", "El vuelo ha sido rechazado en Origen " + vuelo.getNombreAeropuertoOrigen());
            tablaVuelos.refresh();
        } else if (usuarioLogueado.getAeropuerto().equals(vuelo.getAeropuertoDestino())) {
            vuelo.setEstadoAeropuertoDestino(Vuelo.EstadoVuelo.RECHAZADO);
            vuelo.setEstadoVuelo(Vuelo.EstadoVuelo.RECHAZADO);
            vuelo.setPuertaOrigen(null);
            vuelo.setPuertaDestino(null);
            showAlert(Alert.AlertType.CONFIRMATION, "Vuelo Rechazado", "El vuelo ha sido rechazado en Destino " + vuelo.getNombreAeropuertoDestino());
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
            showAlert(Alert.AlertType.CONFIRMATION, "Vuelo Aceptado", "Vuelo aceptado en Origen " + vuelo.getNombreAeropuertoOrigen());
            if (vuelo.getEstadoAeropuertoDestino().equals(Vuelo.EstadoVuelo.ACEPTADO)){
                vuelo.setEstadoVuelo(Vuelo.EstadoVuelo.ACEPTADO);
                showAlert(Alert.AlertType.CONFIRMATION, "Vuelo Aceptado", "Vuelo aceptado totalmente por aeropuerto de Origen " + vuelo.getNombreAeropuertoOrigen() + " y Destino " + vuelo.getNombreAeropuertoDestino());
            }
            tablaVuelos.refresh();
        } else if (usuarioLogueado.getAeropuerto().equals(vuelo.getAeropuertoDestino())) {
            vuelo.setEstadoAeropuertoDestino(Vuelo.EstadoVuelo.ACEPTADO);
            showAlert(Alert.AlertType.CONFIRMATION, "Vuelo Aceptado", "Vuelo aceptado en Destino " + vuelo.getNombreAeropuertoDestino());
            if (vuelo.getEstadoAeropuertoOrigen().equals(Vuelo.EstadoVuelo.ACEPTADO)){
                vuelo.setEstadoVuelo(Vuelo.EstadoVuelo.ACEPTADO);
                showAlert(Alert.AlertType.CONFIRMATION, "Vuelo Aceptado", "Vuelo aceptado totalmente por aeropuerto de Origen " + vuelo.getNombreAeropuertoOrigen() + " y Destino " + vuelo.getNombreAeropuertoDestino());
            }
            tablaVuelos.refresh();
        }
        vueloMgr.updateVuelo(vuelo);
        System.out.println(vuelo.getEstadoVuelo());
        System.out.println(vuelo.getEstadoAeropuertoOrigen());
        System.out.println(vuelo.getEstadoAeropuertoDestino());
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);

        ImageView imageView;
        if (alertType == Alert.AlertType.CONFIRMATION) {
            alert.getButtonTypes().setAll(ButtonType.OK);
            imageView = new ImageView(new Image(getClass().getResourceAsStream("/photos/ok.png")));
        } else if (alertType == Alert.AlertType.ERROR) {
            imageView = new ImageView(new Image(getClass().getResourceAsStream("/photos/error.png")));
        } else {
            imageView = null;
        }

        if (imageView != null) {
            imageView.setFitHeight(48);
            imageView.setFitWidth(48);
            alert.setGraphic(imageView);
        }

        alert.showAndWait();
    }
}

