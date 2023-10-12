package uy.edu.um.airport.ui.Aerolinea;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import org.springframework.beans.factory.annotation.Autowired;
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

@RequestMapping(path = "api/v1/aerolineas")
public class AerolineaController {

    @Autowired
    private AerolineaMgr aerolineaMgr;

    @Autowired
    private UsuarioMgr usuarioMgr;


    @FXML
    private TextField txtIATA;
    @FXML
    private TextField txtICAO;
    @FXML
    private TextField txtNombreAerolinea;
    @FXML
    private TextField txtPais;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellidos;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtConfirmarEmail;
    @FXML
    private TextField txtContraseña;
    @FXML
    private TextField txtConfirmarContraseña;
    @FXML
    private TextField txtPasaporte;
    @FXML
    private DatePicker datePicker;


    @FXML
    private void handleAerolinearegisteryAdmin(ActionEvent actionEvent){
        String iata = txtIATA.getText();
        String icao = txtICAO.getText();
        String nombreAerolinea = txtNombreAerolinea.getText();
        String pais = txtPais.getText();

        Aerolinea aerolinea = new Aerolinea(iata, icao, nombreAerolinea, pais);
        aerolineaMgr.addAerolinea(aerolinea);

        String nombreAdmin = txtNombre.getText();
        String apellidos = txtApellidos.getText();
        String email = txtEmail.getText();
        String confirmarEmail = txtConfirmarEmail.getText();
        String contraseña = txtContraseña.getText();
        String confirmarContraseña = txtConfirmarContraseña.getText();
        String pasaporte = txtPasaporte.getText();
        LocalDate fechaNacimiento = datePicker.getValue();
        Rol rol = Rol.STAFF_AEROLINEA;

        usuarioMgr.addUsuario(new Usuario(nombreAdmin, apellidos, fechaNacimiento, pasaporte, email, contraseña, rol, nombreAerolinea));
    }
}
