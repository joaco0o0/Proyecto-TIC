package uy.edu.um.airport.ui.Usuario;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import uy.edu.um.airport.entities.Aerolinea.Aerolinea;
import uy.edu.um.airport.entities.Aerolinea.AerolineaMgr;
import uy.edu.um.airport.entities.Pasajeros.Pasajeros;
import uy.edu.um.airport.entities.Pasajeros.PasajerosMgr;
import uy.edu.um.airport.entities.Role.Rol;
import uy.edu.um.airport.entities.Usuario.Usuario;
import uy.edu.um.airport.entities.Usuario.UsuarioMgr;
import uy.edu.um.airport.entities.Vuelo.VueloMgr;
import uy.edu.um.airport.session.Session;

import java.io.IOException;
import java.time.LocalDate;

@Component
public class InterfazUsuarioAerolineaController {
    @Autowired
    private AerolineaMgr aerolineaMgr;
    @Autowired
    private VueloMgr VueloMgr;
    @Autowired
    private UsuarioMgr usuarioMgr;

    @Autowired
    private PasajerosMgr pasajerosMgr;

    @Autowired
    private ApplicationContext applicationContext;

    @FXML
    private TextField nombre;
    @FXML
    private TextField apellido;
    @FXML
    private DatePicker fechanacimiento;
    @FXML
    private TextField pasaporte;
    @FXML
    private TextField codigovuelo;

    @FXML
    public void openListaVuelos() {
        loadWindow("/Templates/Inicio/TablaVuelosLista.fxml");
    }

    @FXML
    public void openCrearVuelo() {
        loadWindow("/Templates/Inicio/CrearVuelo.fxml");}

    @FXML
    public void openCrearAvion() {
        loadWindow("/Templates/Inicio/CrearAvion.fxml");}

    @FXML
    public void openAsignarPasajeroAVuelo() {
        loadWindow("/Templates/Inicio/AsignarPasajeroAVuelo.fxml");}
    @FXML
    public void openRegistroPersonalAerolinea() {
        loadWindow("/Templates/Inicio/RegistroPersonalAerolinea.fxml");
    }


    private void loadWindow(String fxmlPath) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
            fxmlLoader.setControllerFactory(applicationContext::getBean);
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void asignarPasajeroAVuelo() {
        Usuario usuarioLogueado = Session.getInstance().getCurrentUser();

        String iata = usuarioLogueado.getAerolinea().getCodigoIATA();
        Aerolinea aerolineaEncontrada = aerolineaMgr.findAerolineaByCodigoIATA(iata);
        Long numeroVuelo = Long.parseLong(codigovuelo.getText());

        uy.edu.um.airport.entities.Vuelo.Vuelo vueloEncontrado = VueloMgr.findVueloByCodigoVuelo(numeroVuelo);

        if (vueloEncontrado == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No se encontro el vuelo");
            return;
        }

        String nombrePasajero = nombre.getText();
        String apellidoPasajero = apellido.getText();
        LocalDate fechaNacimiento = fechanacimiento.getValue();
        String pasaportePasajero = pasaporte.getText();

        Usuario pasajero =  usuarioMgr.findUsuarioByPasaporte(pasaportePasajero);
        if (pasajero == null) {
            pasajero = new Usuario(nombrePasajero, apellidoPasajero, fechaNacimiento, pasaportePasajero, "sistema_aeropuerto@gmail.com","",Rol.USUARIO_FINAL);
            usuarioMgr.addUsuario(pasajero);
            System.out.println("Usuario no existia");
        }

        Pasajeros pasajeros = new Pasajeros(vueloEncontrado, pasajero);
        pasajerosMgr.addPasajero(pasajeros);
        System.out.println("Pasajero agregado");
    }
}