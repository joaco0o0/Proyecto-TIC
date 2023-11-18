package uy.edu.um.airport.ui.Aerolinea;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uy.edu.um.airport.entities.Aerolinea.Aerolinea;
import uy.edu.um.airport.entities.Aerolinea.AerolineaMgr;
import uy.edu.um.airport.entities.Role.Rol;
import uy.edu.um.airport.entities.Usuario.Usuario;
import uy.edu.um.airport.entities.Usuario.UsuarioMgr;
//import uy.edu.um.airport.entities.Aerolinea.AerolineaService;

import java.time.LocalDate;

@Component
public class AerolineaController {

    @Autowired
    private AerolineaMgr aerolineaMgr;
    @Autowired
    private UsuarioMgr usuarioMgr;

    @FXML
    private TextField txtIATAaerolinea;
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
    private TextField txtpasaporte;
    @FXML
    private DatePicker datePicker;





    @FXML
    private void handleAerolinearegister(){
        String iata = txtIATAaerolinea.getText();
        String icao = txtICAO.getText();
        String nombreAerolinea = txtNombreAerolinea.getText();
        String pais = txtPais.getText();

        Aerolinea aerolinea = new Aerolinea(iata, icao, nombreAerolinea, pais);
        aerolineaMgr.addAerolinea(aerolinea);
    }

    @FXML
    private void handleUsuarioAerolineaRegister(){
        String nombre = txtNombre.getText();
        String apellidos = txtApellidos.getText();
        String email = txtEmail.getText();
        String confirmarContraseña = txtConfirmarContraseña.getText();
        String contraseña = txtContraseña.getText();
        String confirmarEmail = txtConfirmarEmail.getText();
        LocalDate fechaNacimiento = datePicker.getValue();
        String codigoIATA = txtIATAaerolinea.getText();
        Aerolinea aerolineaEncontrada = aerolineaMgr.findAerolineaByCodigoIATA(codigoIATA);
        String pasaporte = txtpasaporte.getText();
        Rol rol = Rol.STAFF_AEROLINEA;

        if (aerolineaEncontrada == null) {
            System.err.println("La aerolínea especificada no existe.");
            return;
        }

        if (!email.equals(confirmarEmail)) {
            System.err.println("Los emails especificados no coinciden.");
            return;
        }

        if (!contraseña.equals(confirmarContraseña)) {
            System.err.println("Las contraseñas especificadas no coinciden.");
            return;
        }

        // Crear el nuevo usuario con la aerolínea encontrada
        usuarioMgr.addUsuario(new Usuario(nombre, apellidos, fechaNacimiento, pasaporte, email, contraseña, rol, aerolineaEncontrada, null));

    }




}
