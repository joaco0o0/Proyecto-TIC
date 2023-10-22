package uy.edu.um.airport.ui.Vuelo;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uy.edu.um.airport.entities.Vuelo.Vuelo;
import uy.edu.um.airport.entities.Vuelo.VueloMgr;

import java.time.LocalDate;

@Component
public class VueloController {

    @Autowired
    private VueloMgr vueloMgr;

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
    public void handleVueloRegister(){
        String iata = txtCodigoIATA.getText();
        String icao = txtCodigoICAO.getText();
        String aeropuertoOrigen = AeropuertoOrigen.getText();
        String aeropuertoDestino = AeropuertoDestino.getText();
        LocalDate etd = LocalDate.parse(ETD.getText()); // aca vamos a tener problemas pq van a tener q ingresar la hora perfecto
        LocalDate eta = LocalDate.parse(ETA.getText()); // mejor capaz hacerlos string y listo
        Long numeroVuelo = Long.parseLong(txtNumeroVuelo.getText());

        Vuelo vuelo = new Vuelo(numeroVuelo, iata, icao, aeropuertoOrigen, aeropuertoDestino, etd, eta, null, null, null, null, 0, 0, Vuelo.EstadoVuelo.PENDIENTE_APROBACION);
        vueloMgr.addVuelo(vuelo);
    }
}
