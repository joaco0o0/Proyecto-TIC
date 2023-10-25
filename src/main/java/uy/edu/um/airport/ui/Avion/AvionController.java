package uy.edu.um.airport.ui.Avion;

import javafx.fxml.FXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uy.edu.um.airport.entities.Aerolinea.Aerolinea;
import uy.edu.um.airport.entities.Avion.Avion;
import uy.edu.um.airport.entities.Avion.AvionMgr;
import uy.edu.um.airport.entities.Aerolinea.AerolineaMgr;

import java.awt.*;

@Component
public class AvionController {

    @Autowired
    private AvionMgr avionMgr;

    @Autowired
    private AerolineaMgr aerolineaMgr;

    @FXML
    private TextField txtMatricula;

    @FXML
    private TextField txtCapacidadAsientos;

    @FXML
    private TextField txtCapacidadBultos;

    @FXML
    private TextField txtIATA;

    public void handleAvionRegister(){
        String matricula = txtMatricula.getText();
        int capacidadAsientos = Integer.parseInt(txtCapacidadAsientos.getText());
        int capacidadBultos = Integer.parseInt(txtCapacidadBultos.getText());
        String codigoIATA = txtIATA.getText();
        Aerolinea aerolineaEncontrada = aerolineaMgr.findAerolineaByCodigoIATA(codigoIATA);

        if (aerolineaEncontrada == null) {
            System.err.println("La aerolínea especificada no existe.");
            return;
        }

        if (matricula == null) {
            System.err.println("El campo matrícula es obligatorio.");
            return;
        }

        Avion avion = new Avion(matricula, capacidadAsientos, capacidadBultos, aerolineaEncontrada);
        avionMgr.addAvion(avion);
    }
}
