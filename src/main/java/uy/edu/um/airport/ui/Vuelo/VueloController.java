package uy.edu.um.airport.ui.Vuelo;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uy.edu.um.airport.entities.Aerolinea.Aerolinea;
import uy.edu.um.airport.entities.Aerolinea.AerolineaMgr;
import uy.edu.um.airport.entities.Aeropuerto.Aeropuerto;
import uy.edu.um.airport.entities.Aeropuerto.AeropuertoMgr;
import uy.edu.um.airport.entities.Usuario.Usuario;
import uy.edu.um.airport.entities.Vuelo.VueloMgr;
import uy.edu.um.airport.session.Session;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Component
public class VueloController {

    @Autowired
    private VueloMgr vueloMgr;

    @Autowired
    private AerolineaMgr aerolineaMgr;

    @Autowired
    private AeropuertoMgr aeropuertoMgr;

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

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");


    @FXML
    public void handleVueloRegister(){
        Usuario usuarioLogueado = Session.getInstance().getCurrentUser();

        if (usuarioLogueado == null) {
            mostrarMensaje("Error", "No hay usuario logueado.");
            return;
        }

        String iata = usuarioLogueado.getAerolinea().getCodigoIATA();
        Aerolinea aerolineaEncontrada = aerolineaMgr.findAerolineaByCodigoIATA(iata);

        System.out.println(iata);

        String aeropuertoOrigen = AeropuertoOrigen.getText();
        Aeropuerto aeropuertoEncontradoOrigen = aeropuertoMgr.findAeropuertoByCodigoIATA(aeropuertoOrigen);

        String aeropuertoDestino = AeropuertoDestino.getText();
        Aeropuerto aeropuertoEncontradoDestino = aeropuertoMgr.findAeropuertoByCodigoIATA(aeropuertoDestino);

        if (aeropuertoEncontradoOrigen == null || aeropuertoEncontradoDestino == null) {
            mostrarMensaje("Error", "Uno o ambos códigos de aeropuerto son inválidos.");
            return;
        }

        LocalDateTime etd = parseDateTime(ETD.getText());
        LocalDateTime eta = parseDateTime(ETA.getText());

        if (etd == null || eta == null) {
            mostrarMensaje("Error", "Formato de fecha y hora incorrecto.");
            return;
        }

        Long numeroVuelo = Long.parseLong(txtNumeroVuelo.getText());

        uy.edu.um.airport.entities.Vuelo.Vuelo vuelo = new uy.edu.um.airport.entities.Vuelo.Vuelo(numeroVuelo, aerolineaEncontrada, aerolineaEncontrada, uy.edu.um.airport.entities.Vuelo.Vuelo.EstadoVuelo.PENDIENTE, uy.edu.um.airport.entities.Vuelo.Vuelo.EstadoVuelo.PENDIENTE, uy.edu.um.airport.entities.Vuelo.Vuelo.EstadoVuelo.PENDIENTE, aeropuertoEncontradoOrigen, aeropuertoEncontradoDestino, etd, eta, null, null, null, null, null,null, null,50, 50);
        vueloMgr.addVuelo(vuelo);

        mostrarMensaje("Éxito", "El vuelo ha sido registrado exitosamente.");
    }




    private void mostrarMensaje(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private LocalDateTime parseDateTime(String text) {
        try {
            return LocalDateTime.parse(text, formatter);
        } catch (DateTimeParseException e) {
            mostrarMensaje("Error", "Formato de fecha y hora incorrecto.");
            return null;
        }
    }


}
