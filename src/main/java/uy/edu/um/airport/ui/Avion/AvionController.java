package uy.edu.um.airport.ui.Avion;

import javafx.fxml.FXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uy.edu.um.airport.entities.Avion.Avion;
import uy.edu.um.airport.entities.Avion.AvionMgr;

import java.awt.*;

@Component
public class AvionController {

    @Autowired
    private AvionMgr avionMgr;

    @FXML
    private TextField txtMatricula;

    @FXML
    private TextField txtCapacidad;

    @FXML
    private TextField txtBulto;

    public void handleAvionRegister(){
        String matricula = txtMatricula.getText();
        int capacidad = Integer.parseInt(txtCapacidad.getText());
        int bulto = Integer.parseInt(txtBulto.getText());

        Avion avion = new Avion(matricula, capacidad, bulto, null);
        avionMgr.addAvion(avion);
    }
}
