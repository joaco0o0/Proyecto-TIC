package uy.edu.um.airport.ui.Vuelo;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uy.edu.um.airport.entities.Aerolinea.Aerolinea;
import uy.edu.um.airport.entities.Aerolinea.AerolineaMgr;
import uy.edu.um.airport.entities.Aeropuerto.Aeropuerto;
import uy.edu.um.airport.entities.Aeropuerto.AeropuertoMgr;
import uy.edu.um.airport.entities.Avion.Avion;
import uy.edu.um.airport.entities.Avion.AvionMgr;
import uy.edu.um.airport.entities.Usuario.Usuario;
import uy.edu.um.airport.entities.Vuelo.VueloMgr;
import uy.edu.um.airport.session.Session;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

@Component
public class VueloController {

    @Autowired
    private VueloMgr vueloMgr;

    @Autowired
    private AerolineaMgr aerolineaMgr;

    @Autowired
    private AeropuertoMgr aeropuertoMgr;

    @Autowired
    private AvionMgr avionMgr;

    @FXML
    private TextField txtNumeroVuelo;
    @FXML
    private TextField txtCodigoIATA;
    @FXML
    private TextField txtCodigoICAO;
    @FXML
    private TextField AeropuertoOrigen;
    @FXML
    private TextField AeropuertoDestino;
    @FXML
    private TextField ETD;
    @FXML
    private TextField ETA;
    @FXML
    private TextField txtMatricula;
    @FXML
    private TextField txtCapacidadAsientos;
    @FXML
    private TextField txtCapacidadBultos;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");


    @FXML
    public void handleVueloRegister(){
        Usuario usuarioLogueado = Session.getInstance().getCurrentUser();
        String iata = usuarioLogueado.getAerolinea().getCodigoIATA();
        Aerolinea aerolineaEncontrada = aerolineaMgr.findAerolineaByCodigoIATA(iata);

        String numeroVuelo = txtNumeroVuelo.getText();
        String matricula = txtMatricula.getText();
        String aeropuertoOrigen = AeropuertoOrigen.getText();
        String aeropuertoDestino = AeropuertoDestino.getText();
        String etd = ETD.getText();
        String eta = ETA.getText();
        String capacidadAsientos = txtCapacidadAsientos.getText();
        String capacidadBultos = txtCapacidadBultos.getText();

        Avion avionAsociado = avionMgr.findAvionByMatricula(matricula);

        if(numeroVuelo.isEmpty() || aeropuertoOrigen.isEmpty() || aeropuertoDestino.isEmpty() || etd.isEmpty() || eta.isEmpty() || capacidadAsientos.isEmpty() || capacidadBultos.isEmpty() || matricula.isEmpty()){
            showAlert(Alert.AlertType.ERROR, "Error en el Registro de Vuelo", "Todos los campos son obligatorios.");
            return;
        }

        Long NumeroVuelo;
        try {
            NumeroVuelo = Long.parseLong(txtNumeroVuelo.getText().trim());
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Error en el Registro de Vuelo", "El número de vuelo debe ser un número válido.");
            return;
        }

        if (NumeroVuelo < 0) {
            showAlert(Alert.AlertType.ERROR, "Error en el Registro de Vuelo", "El número de vuelo no puede ser menor a cero.");
            return;
        }

        if(avionAsociado == null ){
            showAlert(Alert.AlertType.ERROR, "Error en el Registro de Vuelo", "El número de matrícula de avión no se encuentra registrado en el sistema.");
            return;
        }

        if(!Objects.equals(aerolineaEncontrada.getCodigoIATA(), avionAsociado.getAerolinea().getCodigoIATA())){
            showAlert(Alert.AlertType.ERROR, "Error en el Registro de Vuelo", "Tu aerolínea no posee un avión con esta matrícula en el sistema.");
            return;
        }

        Aeropuerto aeropuertoEncontradoOrigen = aeropuertoMgr.findAeropuertoByCodigoIATA(aeropuertoOrigen);
        Aeropuerto aeropuertoEncontradoDestino = aeropuertoMgr.findAeropuertoByCodigoIATA(aeropuertoDestino);
        if (aeropuertoEncontradoOrigen == null || aeropuertoEncontradoDestino == null) {
            showAlert(Alert.AlertType.ERROR, "Error de aeropuertos", "Uno o ambos códigos IATA de aeropuerto son inválidos.");
            return;
        }
        if(aeropuertoEncontradoOrigen == aeropuertoEncontradoDestino){
            showAlert(Alert.AlertType.ERROR, "Error de aeropuertos", "Un vuelo no puede tener el mismo aeropuerto de origen y destino.");
            return;
        }

        LocalDateTime etdLocalDateTime = parseDateTime(etd);
        LocalDateTime etaLocalDateTime = parseDateTime(eta);
        if (etdLocalDateTime == null || etaLocalDateTime == null) {
            showAlert(Alert.AlertType.ERROR, "Error de fechas", "El formato de fecha y hora fue ingresado incorrectamente.");
            return;
        }
        if (etdLocalDateTime.isAfter(etaLocalDateTime)) {
            showAlert(Alert.AlertType.ERROR, "Error de fechas", "La fecha y hora de salida (ETD) no pueden ser posteriores a la fecha y hora de llegada (ETA).");
            return;
        }

        int CapacidadAsientos, CapacidadBultos;
        try {
            CapacidadAsientos = Integer.parseInt(txtCapacidadAsientos.getText().trim());
            CapacidadBultos = Integer.parseInt(txtCapacidadBultos.getText().trim());

        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Error en el Registro de Vuelo", "El número de asientos y/o bultos disponibles para el vuelo debe ser válido.");
            return;
        }

        if (CapacidadAsientos < 0) {
            showAlert(Alert.AlertType.ERROR, "Error en el Registro de Vuelo", "La capacidad de asientos disponibles para el vuelo no puede ser menor a cero.");
            return;
        }

        if (CapacidadAsientos > avionAsociado.getCapacidad()) {
            showAlert(Alert.AlertType.ERROR, "Error en el Registro de Vuelo", "La capacidad de asientos disponibles para el vuelo no puede ser mayor a la capacidad que posee el avión. Recuerda que su capacidad máxima de asientos es de: " + avionAsociado.getCapacidad());
            return;
        }

        if (CapacidadBultos < 0) {
            showAlert(Alert.AlertType.ERROR, "Error en el Registro de Vuelo", "La capacidad de bultos disponibles para el vuelo no puede ser menor a cero.");
            return;
        }

        if (CapacidadBultos > avionAsociado.getBulto()) {
            showAlert(Alert.AlertType.ERROR, "Error en el Registro de Vuelo", "La capacidad de bultos disponibles para el vuelo no puede ser mayor a la capacidad que posee el avión. Recuerda que su capacidad máxima de bultos es de: " + avionAsociado.getBulto());
            return;
        }



        uy.edu.um.airport.entities.Vuelo.Vuelo vuelo = new uy.edu.um.airport.entities.Vuelo.Vuelo(NumeroVuelo, aerolineaEncontrada,
                uy.edu.um.airport.entities.Vuelo.Vuelo.EstadoVuelo.PENDIENTE, uy.edu.um.airport.entities.Vuelo.Vuelo.EstadoVuelo.PENDIENTE,
                uy.edu.um.airport.entities.Vuelo.Vuelo.EstadoVuelo.PENDIENTE, aeropuertoEncontradoOrigen, aeropuertoEncontradoDestino, etdLocalDateTime,
                etaLocalDateTime, null, null, avionAsociado, null,null,CapacidadAsientos, CapacidadBultos);
        vueloMgr.addVuelo(vuelo);
        showAlert(Alert.AlertType.CONFIRMATION, "Registro de Vuelo Exitoso", "El vuelo fue registrado en el sistema.");
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

    private LocalDateTime parseDateTime(String text) {
        try {
            return LocalDateTime.parse(text, formatter);
        } catch (DateTimeParseException e) {
            showAlert(Alert.AlertType.ERROR, "Error de fechas", "El formato de fecha y hora fue ingresado incorrectamente.");
            return null;
        }
    }
}
