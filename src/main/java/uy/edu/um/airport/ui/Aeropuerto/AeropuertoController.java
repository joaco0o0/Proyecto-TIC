package uy.edu.um.airport.ui.Aeropuerto;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uy.edu.um.airport.entities.Aeropuerto.Aeropuerto;
import uy.edu.um.airport.entities.Aeropuerto.AeropuertoMgr;

@Component
public class AeropuertoController {

    @Autowired
    private AeropuertoMgr aeropuertoMgr;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtIATA;

    @FXML
    private TextField txtCiudad;

    @FXML
    private TextField txtPais;

    @FXML
    private void handleAeropuertoRegister(){
        String nombre = txtNombre.getText();
        String iata = txtIATA.getText();
        String ciudad = txtCiudad.getText();
        String pais = txtPais.getText();

        Aeropuerto aeropuerto = new Aeropuerto(nombre, iata, ciudad, pais, true);
        aeropuertoMgr.addAeropuerto(aeropuerto);
    }
}
