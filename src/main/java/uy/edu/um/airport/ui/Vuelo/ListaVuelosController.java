package uy.edu.um.airport.ui.Vuelo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import uy.edu.um.airport.entities.Vuelo.Vuelo;
import uy.edu.um.airport.entities.Vuelo.VueloMgr;

@Component
public class ListaVuelosController {

    @FXML
    private Label LabelHora;
    @Autowired
    private VueloMgr vueloMgR;
    @FXML
    private TableView<Vuelo> tablaVuelos;
    @FXML
    private TableColumn<Vuelo, LocalDateTime> columnaHora;
    @FXML
    private TableColumn<Vuelo, String> columnaAerolinea;
    @FXML
    private TableColumn<Vuelo, String> columnaAeropuertoOrigen;
    @FXML
    private TableColumn<Vuelo, String> columnaAeropuertoDestino;
    @FXML
    private TableColumn<Vuelo, String> columnaVuelo;
    @FXML
    private TableColumn<Vuelo, String> columnaPuerta;
    @FXML
    private TableColumn<Vuelo, String> columnaEstado;

    public void initialize() {
        actualizarHora();
        Timeline reloj = new Timeline(new KeyFrame(Duration.seconds(1), e -> actualizarHora()));
        reloj.setCycleCount(Timeline.INDEFINITE);
        reloj.play();

        configurarTabla();
    }

    private void configurarTabla() {
        columnaHora.setCellValueFactory(new PropertyValueFactory<>("ETD"));
        columnaAerolinea.setCellValueFactory(new PropertyValueFactory<>("aerolinea"));
        columnaAeropuertoOrigen.setCellValueFactory(new PropertyValueFactory<>("aeropuertoOrigen"));
        columnaAeropuertoDestino.setCellValueFactory(new PropertyValueFactory<>("aeropuertoDestino"));
        columnaVuelo.setCellValueFactory(new PropertyValueFactory<>("numeroVuelo"));
        columnaEstado.setCellValueFactory(new PropertyValueFactory<>("ETA"));
        columnaPuerta.setCellValueFactory(new PropertyValueFactory<>("puertaOrigen"));

        List<Vuelo> listaDeVuelos = vueloMgR.getVuelosAceptados();
        ObservableList<Vuelo> vuelos = FXCollections.observableArrayList(listaDeVuelos);
        tablaVuelos.setItems(vuelos);
    }

    private void actualizarHora() {
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LabelHora.setText(ahora.format(formatter));
    }
}
