package uy.edu.um.airport.ui.Avion;

import javafx.fxml.FXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uy.edu.um.airport.entities.Aerolinea.Aerolinea;
import uy.edu.um.airport.entities.Avion.Avion;
import uy.edu.um.airport.entities.Avion.AvionMgr;
import uy.edu.um.airport.entities.Aerolinea.AerolineaMgr;
import uy.edu.um.airport.entities.Usuario.Usuario;
import uy.edu.um.airport.persistence.AerolineaRepository;
import uy.edu.um.airport.session.Session;

@Component
public class AvionController {

    @Autowired
    private AvionMgr avionMgr;

    @Autowired
    private AerolineaMgr aerolineaMgr;

    @Autowired
    private AerolineaRepository aerolineaRepository;

    @FXML
    private javafx.scene.control.TextField txtMatricula;

    @FXML
    private javafx.scene.control.TextField txtCapacidadAsientos;

    @FXML
    private javafx.scene.control.TextField txtCapacidadBultos;

    @FXML
    private javafx.scene.control.TextField txtIATA;

    @FXML
    public void handleAvionRegister(){
        // Obtener la aerolínea asociada al usuario logueado
        Usuario usuarioLogueado = Session.getInstance().getCurrentUser();
        Aerolinea aerolineaUsuario = usuarioLogueado.getAerolinea();
        String matricula = txtMatricula.getText();
        int capacidadAsientos = Integer.parseInt(txtCapacidadAsientos.getText());
        int capacidadBultos = Integer.parseInt(txtCapacidadBultos.getText());


        if (matricula == null) {
            System.err.println("El campo matrícula es obligatorio.");
            return;
        }

        if(capacidadAsientos < 0){
            System.err.println("La capacidad de asientos disponibles no puede ser menor a cero.");
            return;
        }

        if(capacidadBultos < 0){
            System.err.println("La capacidad de bultos disponibles no puede ser menor a cero.");
            return;
        }

        Avion avion = new Avion(matricula, capacidadAsientos, capacidadBultos, aerolineaUsuario);
        avionMgr.addAvion(avion);
    }

    @FXML
    public void handleAvionRegisterSuperUsuario(){
        String matricula = txtMatricula.getText();
        int capacidadAsientos = Integer.parseInt(txtCapacidadAsientos.getText());
        int capacidadBultos = Integer.parseInt(txtCapacidadBultos.getText());
        String codigoIATA = txtIATA.getText().trim();

        if (matricula == null || matricula.trim().isEmpty()) {
            System.err.println("El campo matrícula es obligatorio.");
            return;
        }

        if(capacidadAsientos < 0){
            System.err.println("La capacidad de asientos disponibles no puede ser menor a cero.");
            return;
        }

        if(capacidadBultos < 0){
            System.err.println("La capacidad de bultos disponibles no puede ser menor a cero.");
            return;
        }

        // Obtener la aerolínea usando el código IATA proporcionado por el superUsuario
        Aerolinea aerolinea = aerolineaRepository.findByCodigoIATA(codigoIATA);

        if (aerolinea == null) {
            System.err.println("La aerolínea con el código IATA proporcionado no existe.");
            return;
        }

        Avion avion = new Avion(matricula, capacidadAsientos, capacidadBultos, aerolinea);
        avionMgr.addAvion(avion);
    }

}
