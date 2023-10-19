package uy.edu.um.airport.ui.Aerolinea;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import uy.edu.um.airport.entities.Aerolinea.Aerolinea;
import uy.edu.um.airport.entities.Aerolinea.AerolineaMgr;
import uy.edu.um.airport.entities.Role.Rol;
import uy.edu.um.airport.entities.Usuario.Usuario;
import uy.edu.um.airport.entities.Usuario.UsuarioMgr;
//import uy.edu.um.airport.entities.Aerolinea.AerolineaService;

import java.awt.*;
import java.time.LocalDate;
import java.util.List;

@Component
public class AerolineaController {

    @Autowired
    private AerolineaMgr aerolineaMgr;

    @FXML
    private TextField txtIATA;
    @FXML
    private TextField txtICAO;
    @FXML
    private TextField txtNombreAerolinea;
    @FXML
    private TextField txtPais;

    @FXML
    private void handleAerolinearegister(){
        String iata = txtIATA.getText();
        String icao = txtICAO.getText();
        String nombreAerolinea = txtNombreAerolinea.getText();
        String pais = txtPais.getText();

        Aerolinea aerolinea = new Aerolinea(iata, icao, nombreAerolinea, pais);
        aerolineaMgr.addAerolinea(aerolinea);
    }
}
